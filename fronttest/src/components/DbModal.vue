<template>
    <el-dialog title="Editar" v-model="dialogFormVisible" :close-on-click-modal="false" :show-close="false">
        <el-form :model="form">
            <el-form-item label="Id" :label-width="formLabelWidth">
                <el-input :disabled="true" v-model="form.id" auto-complete="off"></el-input>
            </el-form-item>
            <el-form-item label="Username" :label-width="formLabelWidth">
                <el-input :disabled="true" v-model="form.username" auto-complete="off"></el-input>
            </el-form-item>

            <el-form-item label="Email" :label-width="formLabelWidth">
                <el-input :disabled="true" v-model="form.email" auto-complete="off"></el-input>
            </el-form-item>

            <el-form-item label="Celular" :label-width="formLabelWidth">
                <el-input v-model="form.mobile_phone" auto-complete="off"></el-input>
            </el-form-item>
            <el-form-item label="Sexo" :label-width="formLabelWidth">
                <el-input :disabled="false" v-model="form.sex" auto-complete="off"></el-input>
            </el-form-item>
            <el-form-item label="Adresss" :label-width="formLabelWidth">
                <el-input v-model="form.address" auto-complete="off"></el-input>
            </el-form-item>

        </el-form>
        <div slot="footer" class="dialog-footer">
            <el-button :plain="true" type="danger" v-on:click="canclemodal">Cancelar</el-button>
            <el-button :plain="true" @click="updateForm(form)">Salvar</el-button>
        </div>
    </el-dialog>
</template>


<script>
    export default {
        data(){
            return {
                formLabelWidth: '120px',
            }
        },
        props: ['dialogFormVisible', 'form'],

        methods: {
            updateForm: function (formName) {
                let itemId = formName.id;
                let mobile_phone = formName.mobile_phone;
                let email = formName.email;
                let sex = formName.sex;
                this.$axios.put('http://localhost:8089/customers/' + itemId, {
                    mobile_phone: mobile_phone,
                    email: email,
                    sex: sex
                })
                    .then(function (response) {
                        console.log(response);
                        this.form = response.data;

                    })
                    .catch(function (error) {
                        console.log(error);
                    });
                location.reload();
            },
            canclemodal: function () {
                this.$emit('canclemodal');
            }
        }

    }

</script>