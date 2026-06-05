import { createRouter, createWebHistory } from 'vue-router';
import Home from '@/pages/Home.vue';
import About from '@/pages/About.vue';
import Members from '@/pages/Members.vue';
import Videos from '@/pages/Videos.vue';

const router = createRouter({
  history: createWebHistory(),
  routes: [
    {
      path: '/',
      components: {
        default: Home,
        left: HomeLeft,
      },
    },
    {
      path: '/members',
      components: {
        default: Members,
        left: MembersLeft,
        footer: MembersFooter,
      },
    },
  ],
});

export default router;
