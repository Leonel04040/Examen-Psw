
function validarDatos() {
    var boleta = document.getElementById("boleta");
    var nombre = document.getElementById("nombre");
    var primerApellido = document.getElementById("primerapellido");
    var segundoApellido = document.getElementById("segundoapellido");
    var correo = document.getElementById("correo");
    var semestre = document.getElementById("semestre");
    var turno = document.getElementById("turno");
    var password = document.getElementById("password");
    var password2 = document.getElementById("password2")

    if (boleta.value.trim() == "") {
        alert("Debes de ingresar tu boleta");
        return false;
    } else {
        if (nombre.value.trim() == "") {
            alert("Debes de ingresar tu nombre");
            return false;
        } else {
            if (primerApellido.value.trim() == "") {
                alert("Debes de ingresar tu primer apellido");
                return false;
            } else {
                if (correo.value.trim() == "") {
                    alert("Debes de ingresar tu nombre");
                    return false;
                } else {
                    if (semestre.value.trim() == "") {
                        alert("Debes de seleccionar tu semestre");
                        return false;
                    } else {
                        if (turno.value.trim() == "") {
                            alert("Debes de seleccionar tu turno");
                            return false;
                        } else {
                            if (password.value.trim() == "") {
                                alert("Debes de crear una contraseña");
                                return false;
                            }
                        }
                    }
                }
            }
        }
    }
    if (password.value != password2.value) {
        alert("La contraseña no coincide");
        return false;
    }

    return true;

}
