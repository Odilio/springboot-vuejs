<template>
    <div class="container">
        <div class="row justify-content-center">
            <div class="col-md-8">
                <div class="card">
                    <div class="card-header">Cadastro de usuarios</div>
    
                    <div class="card-body">
                        <form @submit="formSubmit">
                        <strong>Name:</strong>
                        <input type="text" class="form-control" v-model="name">
                        <strong>Senha:</strong>
                        <textarea class="form-control" v-model="description"></textarea>
    
                        <button class="btn btn-success">Submit</button>
                        </form>
                        <strong>Output:</strong>
                        <pre>
                        {{output}}
                        </pre>
                    </div>
                    <div class="card-header">Login</div>

                    <div class="card-body">

                        <form @submit="formLogar">
                        <strong>Name:</strong>
                        <input type="text" class="form-control" v-model="username">
                        <strong>password:</strong>
                        <textarea class="form-control" v-model="password"></textarea>
    
                        <button class="btn btn-success">Submit</button>
                        </form>
                        <strong>Token:</strong>
                        <pre>
                        {{token}}
                        </pre>
                    </div>
                   <div class="card-body">

                        <form @submit="inicial">    
                        <button class="btn btn-success">Submit</button>
                        </form>
                    </div> 
                    <ul >
                        <li v-for="user in users"  :key="user.id">
                        {{user}}
                        </li>
                    </ul>   
                    
                </div>
            </div>
        </div>
    </div>
</template>
     
<script>
    export default {
        data() {
            return {
              users: [],
              name: '',
              description: '',
              output: '',
              token: '',
              password: '',
              username: ''
            };
        },
        mounted() {
            console.log('Component mounted.')
             let currentObj = this;
            this.axios.get('http://localhost:8089/questions')
                .then(function (response) {
                    currentObj.users = response.data
                    console.log(response.data)
                });
        },
       
        methods: {
            formSubmit(e) {
                
                e.preventDefault();
                let currentObj = this;
                this.axios.post('http://localhost:8089/questions', {
                    title: this.name,
                    description: this.description
                })
                .then(function (response) {
                    currentObj.output = response.data;
                })
                .catch(function (error) {
                    currentObj.output = error;
                });
            },
             formLogar(e) {
                
                e.preventDefault();
                let currentObj = this;
                this.axios.post('http://localhost:8089/authenticate', {
                    username: this.username,
                    password: this.password
                })
                .then(function (response) {
                    currentObj.token = response.data;
                })
                .catch(function (error) {
                    currentObj.token = error;
                });
            }
            inicial(e) {
                
                e.preventDefault();
                let currentObj = this;
                this.$router.push('/InitialPage')
                .then(function (response) {
                    currentObj.token = response.data;
                })
                .catch(function (error) {
                    currentObj.token = error;
                });
            }
        }
    }
</script>	
