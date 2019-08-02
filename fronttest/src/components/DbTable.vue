<template>
    <div>
        <el-table
                :data="tableData"
                border
                style="width: 100%"
                class="table">
            <el-table-column
                    fixed
                    prop="id"
                    label="Id"
                    width="100">
            </el-table-column>
            <el-table-column
                    prop="username"
                    label="Username"
                    width="120">
            </el-table-column>
            <el-table-column
                    prop="email"
                    label="Email"
                    width="120">
            </el-table-column>
            <el-table-column
                    prop="mobile_phone"
                    label="Celular"
                    width="130">
            </el-table-column>
            <el-table-column
                    prop="sex"
                    label="Sexo"
                    width="100">
            </el-table-column>
            <el-table-column
                    prop="create_datetime"
                    label="Data de Cadastro"
                    width="250"
                    :formatter="formatter">
            </el-table-column>
            <el-table-column
                    fixed="right"
                    label="Operação"
                    >
                <template scope="scope">
                    <el-button @click="editItem(scope.$index, tableData)" type="text" size="large">Editar</el-button>
                </template>
            </el-table-column>
        </el-table>
        <el-pagination class="pagination" layout="prev, pager, next" :total="total" :page-size="pageSize"
                       v-on:current-change="changePage">
        </el-pagination>
        <db-modal :dialogFormVisible="dialogFormVisible" :form="form" v-on:canclemodal="dialogVisible"></db-modal>
                  <ul >
                        <li v-for="user in users"  :key="user.id">
                        {{user}}
                        </li>
                    </ul>  
    </div>
     

</template>

<script>
    import Bus from '../eventBus'
    import DbModal from './DbModal.vue'

    export default {
        data(){
            return {
                users: [],
                tableData: [],
                apiUrl: 'http://localhost:8089/customers/paged',
                total: 0,
                pageSize: 3,
                currentPage: 1,
                mobile_phone: '1',
                sex: '',
                email: '',
                dialogFormVisible: false,
                form: '',
            }
        },
        components: {
            DbModal
        },
        mounted () {
            let currentObj = this;
                  this.$axios.get('http://localhost:8089/customers')
                .then(function (response) {
                    currentObj.users = response.data
                    console.log(response.data)
                });
                   this.$axios.get('http://localhost:8089/customers')
                .then(function (response) {
                    console.log(response.data)
                });
            this.getCustomers();
            Bus.$on('filterResultData', (data) => {
                this.tableData = data.results;
                this.total = '5';
                this.pageSize = data.count;
                this.email = data.email;
                this.mobile_phone = data.mobile_phone;
                this.sex = data.sex;

            });
        },

        methods: {

            dialogVisible: function () {
                this.dialogFormVisible = false;
            },

            getCustomers: function () {
                let currentObj = this;
                this.$axios.get(this.apiUrl, {
                    params: {
                        page: this.currentPage,
                        size: this.pageSize
                    }
                }).then(function (response) {
                    currentObj.tableData = response.data;
                    currentObj.total = response.data.total;
                    currentObj.mobile_phone = response.data.mobile_phone;
                    console.log(response.data.results+'  teste');
                }).catch(function (response) {
                    console.log(response)
                });
            },
            changePage: function (currentPage) {
                this.currentPage = currentPage;
                this.getCustomers()
            },
            editItem: function (index, rows) {
                this.dialogFormVisible = true;
                const id = rows[index].id;
                console.log(id+'outro')
                const idurl = 'http://localhost:8089/customers/' + id;
                this.$axios.get(idurl).then((response) => {
                    this.form = response.data;
                    console.log(idurl+'finish')
                }).catch(function (response) {
                    console.log(response)
                });
            },

            formatter(row, column) {
                let data = this.$moment(row.create_datetime, this.$moment.ISO_8601);
                return data.format('YYYY-MM-DD')
            },
        }
    }
</script>

<style>
    .table {
        margin-top: 30px;
    }

    .pagination {
        margin-top: 10px;
        float: right;
    }

</style>