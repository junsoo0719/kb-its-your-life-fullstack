import { fileURLToPath, URL } from 'node:url';

import { defineConfig } from 'vite';
import vue from '@vitejs/plugin-vue';
import vueDevTools from 'vite-plugin-vue-devtools';
import { proxyRefs } from 'vue';

// https://vite.dev/config/
export default defineConfig({
  plugins: [vue(), vueDevTools()],
  resolve: {
    alias: {
      '@': fileURLToPath(new URL('./src', import.meta.url)),
    },
  },
  server: {
    proxy: {
      '/api': {
        target:
          'https://port-0-kb7-back-node-mmc3pmla14c7b8b4.sel3.cloudtype.app/',
        changeOrigin: true,
        rewrite: (path) => path.replace(/^\/api/, ''),
      },
      '/ex-api': {
        target:
          'https://port-0-kb7-back-node-mmc3pmla14c7b8b4.sel3.cloudtype.app/',
        changeOrigin: true,
        rewrite: (path) => path.replace(/^\/ex-api/, ''),
      },
    },
  },
});
