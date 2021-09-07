import { createRouter, createWebHistory } from 'vue-router'

import admin from "../components/admin/Admin.vue";

const history = createWebHistory();

const router = createRouter({
    history,
    routes: [
        {
            path: '/admin',
            name: 'admin',
            component: admin
        }
    ]
});

export default router