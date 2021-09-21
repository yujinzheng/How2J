import axios from "axios";
import da from "element-plus/packages/locale/lang/da";

const getCategoryListUrl = '/tmall-vue/admin/category/list'
const addCategoryUrl = '/tmall-vue/admin/category/add'
const deleteCategoryUrl = '/tmall-vue/admin/category/delete'
const updateCategoryUrl = '/tmall-vue/admin/category/update'

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

function categoryListGet(currentPage, pageSize) {
    return axios({
        url: getCategoryListUrl + '?start=' + currentPage + '&size=' + pageSize,
        method: 'GET'
    })
}

function categoryAdd(data={}) {
    const requestData = new FormData()
    requestData.append('name', data.name)
    return axios({
        url: addCategoryUrl,
        method: 'POST',
        headers: {
            'Content-Type': 'multipart/form-data'
        },
        data: requestData
    })
}

function categoryDelete(data={}) {
    return axios({
        url: deleteCategoryUrl,
        method: 'DELETE',
        data
    })
}

function categoryUpdate(data={}) {
    const requestData = new FormData()
    requestData.append('category', JSON.stringify(data));
    return axios({
        url: updateCategoryUrl,
        method: 'POST',
        headers: {
            'Content-Type': 'multipart/form-data'
        },
        data: requestData
    })
}

export {categoryListGet, categoryAdd, categoryDelete, categoryUpdate}