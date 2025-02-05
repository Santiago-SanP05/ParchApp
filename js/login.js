
const email = "juan.perez@example.com";
const username = "juanp";
const password = "noraaaa";


const loginUsuario = async (username, email, password) => {
    const token = "Bearer eyJhbGciOiJIUzUxMiJ9.eyJqdGkiOiJjYW1wdXNjbCIsInN1YiI6Imp1YW4ucGVyZXpAZXhhbXBsZS5jb20iLCJpYXQiOjE3Mzg2MzkyNDUsImV4cCI6MTczOTUwMzI0NX0.hdqEnZ7xfHLC_oagBkwjsHcIE4OKltFGt5sjstr2DMDN1WqtZH6WQhHCXomkCHvLZvJhKD2QRRtHOGVQ7Lbalw";
    try {
        var username = document.querySelector("#usuario").value;
    var email = document.querySelector("#email").value;
    var password = document.querySelector("#contraseña").value;
    
    console.log(username,email,password);

        const respuesta = await fetch("http://localhost:3002/api/auth/login", {
            method: "POST",
            headers: {
                "Content-Type": "application/json"  
            },
            body: JSON.stringify({
                username: username,
                email: email,
                password: password
            }) 
        });

        if (!respuesta.ok) {
            const errorText = await respuesta.text(); 
            console.error(`Error en la solicitud: ${respuesta.status}`, errorText);
            alert("Usuario o contraseña incorrectos.");
            return false;
        }

        const data = await respuesta.json();
        localStorage.setItem("token", data.token);
        localStorage.setItem("email", data.email);
        localStorage.setItem("id", data.idUser);
        console.log("http://localhost:3002/api/comment");
        console.log(data);
        
        alert("Inicio de sesión exitoso.");
        window.location.href = "/html/inicio.html";
        return email, true;
        
    } catch (error) {
        console.error("Error al iniciar sesión:", error);
        alert("Ocurrió un error, intenta nuevamente.");
        return false;
    }
};


const entrar = document.querySelector('#principal'); 
entrar.addEventListener("click", loginUsuario);









function vistaregistrar(){
    var contenidoRegistrar = document.querySelector(".contLogin");
    contenidoRegistrar.innerHTML= "";
    contenidoRegistrar.innerHTML=`
         <style>
        .inputRegister {
            flex-direction: row;

        }
        .login {
            width: 80%;
        }
        .contLogin{
            width: 80%;
        }


    </style>
        <div class="columnTitulo">
        <h1 id="titulo">ParchApp</h1>
                    <h1>Registrar</h1>
        </div>
                    <div class="inputRegister">
                    <p>Nombre usuario: <input id="editnombreusuario" type="text" placeholder="${"data.name"}"></p>
                    <p>Usuario: <input id="editusuario" type="text" placeholder="${"data.nameUser"}"></p>
                    <p>Correo: <input id="editemail" type="email" placeholder="${"data.email"}"></p>
                    <p>Biografia: <input id="editBio" type="text" placeholder="${"data.biography"}"></p>
                    <p>Url foto: <input id="editUrlImagen" type="url" placeholder="${"data.urlPhoto"}"> </p>
                    <p>Contraseña: <input id="editcontraseña" type="password" placeholder="**********"></p>
                    <p>Confiramar Contraseña: <input id="editcontraseñaConfirmar" type="password" placeholder="confirmar contraseña"></p>
                    </div>
                    
                   <button id="principal">Entrar</button>
                    <a href="#" id="registrar">Registrarme</a>
    `;
}

const registrar = document.querySelector("#registrar");
registrar.addEventListener("click", vistaregistrar);


/*
<h1 id="titulo">ParchApp</h1>
                    <h1>Bienvenido</h1>
                    <div class="inputLogin">
                    <input id="usuario" type="text" placeholder="Usuario">
                    <input id="email" type="email" placeholder="usuario@gmail.com">
                    <input id="contraseña" type="password" placeholder="***********">
                    </div>
                    
                   <button id="principal">Entrar</button>
                    <a href="#" id="registrar">Registrarme</a>
*/



/*

<h1 id="titulo">ParchApp</h1>
                    <h1>Registrar</h1>
                    <div class="inputRegister">
                    <input id="usuario" type="text" placeholder="Usuario">
                    <input id="email" type="email" placeholder="usuario@gmail.com">
                    <input id="contraseña" type="password" placeholder="***********">
                    </div>
                    
                   <button id="principal">Entrar</button>
                    <a href="#" id="registrar">Registrarme</a>

*/