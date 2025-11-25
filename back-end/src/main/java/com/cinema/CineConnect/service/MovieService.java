package com.cinema.CineConnect.service;

import com.cinema.CineConnect.model.DTO.MovieRecord;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.simple.JdbcClient;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.List;
import java.util.UUID;

@Service
public class MovieService {

    private final JdbcClient jdbcClient;
    private final Path rootLocation; // Pasta de uploads

    // Mapeia o resultado do banco para o Record
    private static final RowMapper<MovieRecord> MOVIE_ROW_MAPPER = (rs, rowNum) -> new MovieRecord(
            rs.getInt("id"),
            rs.getString("title"),
            rs.getString("synopsis"),
            rs.getString("genre"),
            rs.getInt("duration"),
            rs.getBigDecimal("rating"),
            rs.getString("image_filename")
    );

    @Autowired
    public MovieService(JdbcClient jdbcClient, @Value("${file.upload-dir}") String uploadDir) {
        this.jdbcClient = jdbcClient;
        this.rootLocation = Paths.get(uploadDir);
        try {
            Files.createDirectories(rootLocation);
        } catch (IOException e) {
            throw new RuntimeException("Não foi possível criar pasta de uploads", e);
        }
    }

    public MovieRecord saveMovieWithImage(MovieRecord movie, MultipartFile file) throws IOException {
        // 1. Lidar com o Arquivo (Salvar no Disco)
        String originalFilename = StringUtils.cleanPath(file.getOriginalFilename());

        // Dica: Adicionar um UUID evita que dois filmes com "poster.jpg" se sobrescrevam
        String uniqueFilename = UUID.randomUUID().toString() + "_" + originalFilename;

        Path destinationFile = this.rootLocation.resolve(Paths.get(uniqueFilename))
                .normalize().toAbsolutePath();

        try (InputStream inputStream = file.getInputStream()) {
            Files.copy(inputStream, destinationFile, StandardCopyOption.REPLACE_EXISTING);
        }

        // 2. Salvar no Banco de Dados
        String sql = """
                INSERT INTO movies (title, synopsis, genre, duration, rating, image_filename)
                VALUES (?, ?, ?, ?, ?, ?)
                RETURNING id
                """;

        Integer generatedId = jdbcClient.sql(sql)
                .params(
                        movie.title(),
                        movie.synopsis(),
                        movie.genre(),
                        movie.duration(),
                        movie.rating(),
                        uniqueFilename // Salvamos o nome gerado
                )
                .query(Integer.class)
                .single();

        // Retorna o objeto completo com o novo ID e o nome da imagem
        return new MovieRecord(
                generatedId, movie.title(), movie.synopsis(), movie.genre(),
                movie.duration(), movie.rating(), uniqueFilename
        );
    }

    public List<MovieRecord> findAll() {
        return jdbcClient.sql("SELECT * FROM movies").query(MOVIE_ROW_MAPPER).list();
    }

    public MovieRecord findById(Integer id) {
        return jdbcClient.sql("SELECT * FROM movies WHERE id = ?")
                .param(id)
                .query(MOVIE_ROW_MAPPER)
                .single();
    }
}