
localStorage.clear();
var mostrarLaBienvenida = document.querySelector(".central")
mostrarLaBienvenida.addEventListener("click", mostrarBienvenida());


function mostrarBienvenida(){
    mostrarLaBienvenida.innerHTML = `
    <nav class="login">
                <section class="contLogin">
                    
                    <h1 id="titulo">ParchApp</h1>
                    <h1 id="tituloBienvenido">Bienvenido</h1>
                    <div class="inputLogin">
                    <input class="inputPlace" id="usuario" type="text"placeholder="Usuario">
                    <input class="inputPlace" id="email" type="email" placeholder="usuario@gmail.com">
                    <input class="inputPlace" id="contraseña" type="password" placeholder="***********">
                    </div>
                    
                   <button id="principal">Entrar</button>
                    <a href="#" id="registrar">Registrarme</a>

                </section>
            </nav>

    `;
    const entrar = document.querySelector('#principal'); 
entrar.addEventListener("click", loginUsuario);
}



  
async function loginUsuario(){
    
    try {
        var username = document.querySelector("#usuario").value;
    var email = document.querySelector("#email").value;
    var password = document.querySelector("#contraseña").value;
    

        const respuesta = await fetch("http://localhost:8080/parchap-0.0.1-SNAPSHOT/api/auth/login", {
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
        localStorage.setItem("name", data.nameUser);

        
        alert("Inicio de sesión exitoso.");
        window.location.href = "/html/inicio.html";
        return email, true;
        
    } catch (error) {
        console.error("Error al iniciar sesión:", error);
        alert("Ocurrió un error, intenta nuevamente.");
        return false;
    }
};












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
            height: auto;
            
        }
        .contLogin{
            width: 80%;
        }


    </style>
        <div class="columnTitulo">
        <h1 id="titulo">ParchApp</h1>
                    <h1 id="registrar">Registrar</h1>
        </div>
                    <div class="inputRegister">
                    <p>Nombre usuario: <input id="editnombreusuario" type="text" placeholder="Digita tus nombres"></p>
                    <p>Usuario: <input id="editusuario" type="text" placeholder="coloca tu usuario"></p>
                    <p>Correo: <input id="editemail" type="email" placeholder="digita tu corre"></p>
                    <p>Biografia: <input id="editBio" type="text" placeholder="Digite su biografia"></p>
                    <p>Contraseña: <input id="editcontraseña" type="password" placeholder="**********"></p>
                    <p>Confiramar Contraseña: <input id="editcontraseñaConfirmar" type="password" placeholder="confirmar contraseña"></p>
                    </div>
                    
                   <button id="registroUsuario">Registrarme</button>
                   <a href="/index.html" id="return">Return</a>

    `;
    var registrar = document.querySelector("#registroUsuario");
registrar.addEventListener("click", registrarUsuario);


}

const registrar = document.querySelector("#registrar");
registrar.addEventListener("click", vistaregistrar);




async function registrarUsuario(){
    const token = "Bearer eyJhbGciOiJIUzUxMiJ9.eyJqdGkiOiJjYW1wdXNjbCIsInN1YiI6Imp1YW4ucGVyZXpAZXhhbXBsZS5jb20iLCJpYXQiOjE3Mzg2MzkyNDUsImV4cCI6MTczOTUwMzI0NX0.hdqEnZ7xfHLC_oagBkwjsHcIE4OKltFGt5sjstr2DMDN1WqtZH6WQhHCXomkCHvLZvJhKD2QRRtHOGVQ7Lbalw";
    const editnombreusuario = document.querySelector("#editnombreusuario").value.trim();
    const editusuario = document.querySelector("#editusuario").value.trim();
    const editemail = document.querySelector("#editemail").value.trim();
    const editBio = document.querySelector("#editBio").value.trim();
    const editcontraseña = document.querySelector("#editcontraseña").value.trim();
    const editcontraseñaConfirmar = document.querySelector("#editcontraseñaConfirmar").value.trim();

  
    if (!editnombreusuario || !editusuario || !editemail || !editcontraseña || !editcontraseñaConfirmar || !editBio) {
      alert("Por favor, completa todos los campos obligatorios.");
      return;
  }
    const validaemail = /^[^\s@]+@[^\s@]+\.[^\s@]+$/;
   
    if (editemail && !validaemail.test(editemail)) {
      alert("El email no concide con los parametros necesario recuerda el formato hola@gmail.com");
      return;
    }
    if (editcontraseña !== editcontraseñaConfirmar) {
      alert("La contraseña de confirmación no coincide con la contraseña ingresada.");
      return;
    }
  
    const url = 'http://localhost:8080/parchap-0.0.1-SNAPSHOT/api/users';
  
      const datosActualizados = {
        name: editnombreusuario,
        nameUser: editusuario,
        email: editemail,
        password: editcontraseña,
        urlPhoto: "https://cdn-icons-png.flaticon.com/512/6326/6326055.png",
        biography: editBio
        
      };
  
      try {
          const respuesta = await fetch(url, {
              method: 'POST',
              headers: {
                "Authorization": `Bearer ${token}`,
                  'Content-Type': 'application/json' // Indica que el cuerpo está en JSON
              },
              body: JSON.stringify(datosActualizados) // Convierte el objeto a JSON
          });
          

          if (respuesta.ok) {
              const datos = await respuesta.json();
              
              alert('Datos registrados:', datos);
          } else {
              console.error('Error al actualizar:', respuesta.status);
          }
          mostrarBienvenida();
          
      } catch (error) {
          console.error('Error de red:', error);
      }
  
  }
