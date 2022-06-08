function validarDatosRM(){
    var id = document.getElementById("id");
    var marca = document.getElementById("marca");
    var modelo = document.getElementById("modelo");
    var laboratorio = document.getElementById("laboratorio");
    
    if(id.value.trim()==""){
        alert("Debes de introducir el numero de serie");
        return false;
    }else{
        if(marca.value.trim()==""){
            alert("Debes de introducir la marca de la maquina");
            return false;
        }else{
            if(modelo.value.trim()==""){
                alert("Debes de ingresar el modelo de la maquina");
                return false;
            }else{
                if(laboratorio.value.trim()==""){
                    alert("Debes de introducir el laboratorio");
                    return false;
                }
            }
        }
    }
    return true;
}