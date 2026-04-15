import About from '@/pages/About..vue';
import EditTodo from '@/pages/EditTodo.vue';
import NotFound from '@/pages/NotFound.vue';
import TodoList from '@/pages/TodoList.vue';
import { createRouter, createWebHistory } from 'vue-router';

const router = createRouter({
  history: createWebHistory(import.meta.env.BASE_URL),
  routes: [
    { path: '/', component: Home },
    {
      path: '/about',
      component: About,
    },
    {
      path: '/todos',
      component: TodoList,
    },
    {
      path: '/todos/add',
      component: AddTodo,
    },
    {
      path: '/todos/edit/:id',
      component: EditTodo,
    },
    {
      path: '/:paths(.*)*',
      component: NotFound,
    },
  ],
});

export default router;
