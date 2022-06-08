
function validarDatosIniciarSesion(){
    var boleta= document.getElementById("boleta");
    var password= document.getElementById("password");
    
    if(boleta.value.trim()==""){
        alert("Debes de ingresar tu boleta");
        return false;
    }else{
        if(password.value.trim()==""){
            alert("Debes de ingresar tu contraseña");
            return false;
        }
    }
    return true;
}