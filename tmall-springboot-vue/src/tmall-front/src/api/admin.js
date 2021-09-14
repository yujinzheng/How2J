import axios from "axios";

const getCategoryListUrl = '/tmall-vue/admin/category/list'
const addCategoryUrl = '/tmall-vue/admin/category/add'

// 添加一个拦截器，回调函数会在发出请求时执行
// config: 请求配置
axios.interceptors.request.use(config => {
    // TODO 在发送请求前需要做的动作，此时暂时啥都不做
    config.headers.Authorization = "test";
    return config;
}, error => {
    // TODO 如果请求错误，这里填写错误信息，此时暂时啥都不做
    return Promise.reject(error);
})

// 响应拦截器
axios.interceptors.response.use(response => {
    // 获取请求时执行的操作，当前只返回数据
    return response.data;
}, error => {
    // TODO 如果请求错误，这里填写错误信息，此时暂时啥都不做
    return Promise.reject(error);
})

function getCategoryList() {
    return axios({
        url: getCategoryListUrl,
        method: 'GET'
    })
}

function addCategory(data={}) {
    return new Promise((resolve, reject) => {
        axios({
            method: 'post',
            addCategoryUrl,
            data
        }).then(data => {
            resolve(data)
        }).catch(err => {
            reject(err);
        })
    })
}

export {getCategoryList, addCategory}