<template>
    <div class="container">
        <div class="row justify-content-center">
            <div class="col-md-8">
                <div class="card">
                    <div class="card-header">Pagina inicial</div>
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
      
        }
    }
</script>	
