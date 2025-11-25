import { IconBrandInstagram, IconBrandTwitter, IconBrandYoutube } from '@tabler/icons-react';
import { ActionIcon, Container, Group } from '@mantine/core';

// Removemos a importação do arquivo CSS modular, pois não será mais usado
// import classes from './FooterSocial.module.css'; 

export function FooterSocial() {
  return (
    // Substitui classes.footer por classes Tailwind para definir o estilo do rodapé
    <div className="border-t border-gray-200 py-4 bg-gray-50"> 
      {/* Container do Mantine já adiciona padding horizontal e largura máxima.
        Adicionamos classes flex e justify-between para layout de linha
      */}
      <Container className="flex items-center justify-between">
        <p>CineConnect</p>
        
        {/* Substitui classes.links. Usamos 'gap-0' do Tailwind para anular 
            qualquer gap padrão, mas o Group do Mantine já controla isso. 
            A classe 'flex-end' agora é 'justify-end' no Group. 
            'wrap="nowrap"' é o padrão do Tailwind, mas mantido no Group para clareza.
        */}
        <Group gap={0} justify="flex-end" wrap="nowrap">
          {/* ActionIcon e Icon são componentes Mantine/Tabler e não precisam de classes Tailwind aqui */}
          <ActionIcon size="lg" color="gray" variant="subtle" aria-label="Twitter">
            <IconBrandTwitter size={18} stroke={1.5} />
          </ActionIcon>
          <ActionIcon size="lg" color="gray" variant="subtle" aria-label="YouTube">
            <IconBrandYoutube size={18} stroke={1.5} />
          </ActionIcon>
          <ActionIcon size="lg" color="gray" variant="subtle" aria-label="Instagram">
            <IconBrandInstagram size={18} stroke={1.5} />
          </ActionIcon>
        </Group>
      </Container>
    </div>
  );
}