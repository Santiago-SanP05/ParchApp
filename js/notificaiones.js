const notificaiones = document.querySelector("#notificaiones");
notificaiones.addEventListener("click", apartadoNotificaiones)

var urlUser2 = "http://localhost:3002/api/users"
var token2 = localStorage.getItem("token");
var id = localStorage.getItem("id");


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
            <p>Te ha dado Reaccionado tu publicacion</p>
            <div class="foto">
              <img src="${"https://c0.klipartz.com/pngpicture/81/570/gratis-png-perfil-logo-iconos-de-computadora-usuario-usuario-thumbnail.png"}" alt="Foto de usuario">
              <h1>El usuario: ${"aqui el nombre del usuario"}</h1>
            </div>
            
            <p>${"aqui va la fecha de reaccion"}</p>
          </div>
    `;
    var eventoNotiComentarios = document.querySelector(".notificacionesComentarios")
    eventoNotiComentarios.addEventListener("click", function () {
        verNotificacionesComentarios();
    })
    var eventoNotiicacionesReacciones = document.querySelector(".notificacionesReacciones")
    eventoNotiicacionesReacciones.addEventListener("click",verNotificacionesReacciones)
}


async function verNotificacionesComentarios() {
    console.log(urlUser2)
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

                    limpiar.innerHTML += `
                        <div class="etiquetasNotificaciones">
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
    console.log("hola")
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
                    const contenidousuario = await respuestaUser.json();

                    limpiar.innerHTML += `
                        <div class="etiquetasNotificaciones">
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
    console.log("hola")
    let limpiar = document.querySelector(".etiquetasNotificaciones");
    limpiar.innerHTML = "";
    try {
        const respuesta = await fetch(urlUser2 + "/" + id + "/followers", {
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
                    const contenidousuario = await respuestaUser.json();

                    limpiar.innerHTML += `
                        <div class="etiquetasNotificaciones">
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