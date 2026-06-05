import { createRouter, createWebHistory } from 'vue-router';
import HomeView from '../views/HomeView.vue';

const router = createRouter({
  history: createWebHistory(import.meta.env.BASE_URL),
  routes: [
    {
      path: '/',
      name: 'home',
      component: HomeView,
    },
    {
      path: '/about',
      name: 'about',
      component: () => import('@/views/AboutView.vue'),
    },
    {
      path: '/dynamic/:mood',
      name: 'dynamic',
      component: () => import('@/views/DynamicView.vue'),
      props: true,
    },
    {
      path: '/dynamic-watch/:mood',
      name: 'dynamic-watch',
      component: () => import('@/views/DynamicWatchView.vue'),
      props: true,
    },
    {
      path: '/cors',
      name: 'cors',
      component: () => import('@/views/CorsView.vue'),
    },
    {
      path: '/cors-ex',
      name: 'cors-ex',
      component: () => import('@/views/CorsExView.vue'),
    },
    {
      path: '/:pathMatch(.*)*',
      name: 'notfound',
      component: () => import('@/views/NotFound.vue'),
    },
  ],
});

export default router;
