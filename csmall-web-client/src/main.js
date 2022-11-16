import Vue from 'vue'
import App from './App.vue'
import router from './router'
import store from './store'
import ElementUI from 'element-ui';
import 'element-ui/lib/theme-chalk/index.css';
import axios from 'axios';
import jsCookie from 'js-cookie'
import qs from 'qs';
import wangEditor from 'wangeditor';

Vue.prototype.qs = qs;
Vue.prototype.$cookie = jsCookie;
Vue.prototype.axios = axios;
Vue.prototype.wangEditor = wangEditor;
Vue.use(ElementUI);
Vue.config.productionTip = false

new Vue({
    router,
    store,
    render: h => h(App)
}).$mount('#app')
