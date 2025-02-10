const notificaiones = document.querySelector("#notificaiones");
notificaiones.addEventListener("click", apartadoNotificaiones)

var urlUser2 = "http://localhost:3002/api/users"
var token2 = localStorage.getItem("token");
var id = localStorage.getItem("id");
var urlfollowers = "http://localhost:3002/api/follower"


function apartadoNotificaiones() {
    let limpiar = document.querySelector(".publicacion");
    limpiar.innerHTML = "";
    limpiar.innerHTML = `
        <div class="botonesNotificacionesCR"> 
            <button class="notificacionesReacciones">Notificaciones Reacciones</button>
          <button class="notificacionesComentarios">Notificaiones Comentarios</button>
          <button class="notificacionesSeguimiento">Notificaiones Seguidores</button>
          </div>
          <div class="etiquetasNotificaciones">

          </div>
    `;
    var eventoNotiComentarios = document.querySelector(".notificacionesComentarios")
    eventoNotiComentarios.addEventListener("click", function () {
        verNotificacionesComentarios();
    })
    var eventoNotiicacionesReacciones = document.querySelector(".notificacionesReacciones")
    eventoNotiicacionesReacciones.addEventListener("click",verNotificacionesReacciones)
    var eventoNotiicacionesSeguidores = document.querySelector(".notificacionesSeguimiento")
    eventoNotiicacionesSeguidores.addEventListener("click",verNotificacionesSeguidores)
}


async function verNotificacionesComentarios() {

    let limpiar = document.querySelector(".etiquetasNotificaciones");
    limpiar.innerHTML = "";
    try {
        const respuesta = await fetch(urlUser2 + "/" + id + "/posts", {
            method: 'GET', // Método HTTP PUT
            headers: {
                "Authorization": `Bearer ${token2}`,
                'Content-Type': 'application/json' // Indica que el cuerpo está en JSON
            },
        });

        if (respuesta.ok) {
            const notificacionesComentarios = await respuesta.json();
            console.log('Notificaiones:', notificacionesComentarios);
            for (const element of notificacionesComentarios) {
                for (const element2 of element.coments) {
                    const respuestaUser = await fetch(urlUser2 + "/" + element2.idUser , {
                        method: 'GET', // Método HTTP PUT
                        headers: {
                            "Authorization": `Bearer ${token2}`,
                            'Content-Type': 'application/json' // Indica que el cuerpo está en JSON
                        },
                    });
                    const contenidousuario = await respuestaUser.json();
                    if (element2.idUser == id) {
                        
                        continue
                    }
                    limpiar.innerHTML += `
                        <div class="etiquetasNotificacion">
                            <p>Te ha dado comentado tu publicacion</p>
                            <div class="foto">
                            <img src="${contenidousuario.urlPhoto}" alt="Foto de usuario">
                            <h1>El usuario: ${contenidousuario.nameUser}</h1>
                            </div>
                            <p>${element2.text}</p>
                            <p>${element2.publicationDate}</p>
                        </div>
    `;
                }
            }
        } else {
            console.error('Error al mostrar:', respuesta.status);
        }



    } catch (error) {

    }

}



async function verNotificacionesReacciones() {
    let limpiar = document.querySelector(".etiquetasNotificaciones");
    limpiar.innerHTML = "";
    try {
        const respuesta = await fetch(urlUser2 + "/" + id + "/posts", {
            method: 'GET', // Método HTTP PUT
            headers: {
                "Authorization": `Bearer ${token2}`,
                'Content-Type': 'application/json' // Indica que el cuerpo está en JSON
            },
        });

        if (respuesta.ok) {
            const notificacionesComentarios = await respuesta.json();
            console.log('Notificaiones:', notificacionesComentarios);
            for (const element of notificacionesComentarios) {
                for (const element2 of element.reactions) {
                    const respuestaUser = await fetch(urlUser2 + "/" + element2.idUser , {
                        method: 'GET', // Método HTTP PUT
                        headers: {
                            "Authorization": `Bearer ${token2}`,
                            'Content-Type': 'application/json' // Indica que el cuerpo está en JSON
                        },
                    });
                    
                    if (element2.idUser == id) {
                        continue
                    }
                    const contenidousuario = await respuestaUser.json();

                    limpiar.innerHTML += `
                        <div class="etiquetasNotificacion">
                            <p>Te ha dado comentado tu publicacion</p>
                            <div class="foto">
                            <img src="${contenidousuario.urlPhoto}" alt="Foto de usuario">
                            <h1>El usuario: ${contenidousuario.nameUser}</h1>
                            </div>
                            <p>${element2.publicationDate}</p>
                        </div>
    `;
                }
            }
        } else {
            console.error('Error al mostrar:', respuesta.status);
        }
    } catch (error) {

    }
}

async function verNotificacionesSeguidores() {
    let limpiar = document.querySelector(".etiquetasNotificaciones");
    limpiar.innerHTML = "";
    try {
        const respuesta3 = await fetch(urlUser2 + "/" + id + "/followers", {
            method: 'GET', // Método HTTP PUT
            headers: {
                "Authorization": `Bearer ${token2}`,
                'Content-Type': 'application/json' // Indica que el cuerpo está en JSON
            },
        });

        if (respuesta3.ok) {
            const notificacionesSeguidores= await respuesta3.json();
            
          
            for (const element of notificacionesSeguidores) {
                
                    if (element.idUser == id) {
                        continue
                    }
                    limpiar.innerHTML += `
                        <div class="etiquetasNotificacion">
                            <p>Te ha empezado seguir</p>
                            <div class="foto">
                            <img src="${element.urlPhoto}" alt="Foto de usuario">
                            <h1>El usuario: ${element.nameUser}</h1>
                            </div>
                        </div>
    `;
                
            }
        } else {
            console.error('Error al mostrar:', respuesta.status);
        }



    } catch (error) {

    }

}