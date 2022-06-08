function validarDatosRP(){
    var maquinaId = document.getElementById("MaquinaID");
    var problemaTecnico = document.getElementById("problematec");
    
    
    if(problemaTecnico.value.trim() == ""){
        alert("Debe de ingresar la problematica de la maquina");
        return false;
    }else{
        if(maquinaId.value.trim() == ""){
            alert("Debe de seleccionar el id de una maquina");
        return false;
        }
    }
    return true;
}