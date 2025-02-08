var urlUser3 = 'http://localhost:3002/api/users';
var token3 = localStorage.getItem("token");
var id = localStorage.getItem("id");
document.addEventListener("DOMContentLoaded", function () {
    let inputBusqueda = document.querySelector(".encabezado input");

    if (inputBusqueda) {
        inputBusqueda.addEventListener("keypress", function (event) {
            if (event.key === "Enter") {
                let textoBusqueda = this.value.trim();
                    buscarPublicaciones(textoBusqueda);
            }
        });
    } else {
        console.error("❌ No se encontró el input de búsqueda.");
    }
});

// Función de búsqueda de prueba
async function buscarPublicaciones(texto) {

    try {
      const response = await fetch(urlUser3+"/user" +"/"+texto, {
        method: "GET",
        headers: {
          "Authorization": `Bearer ${token3}`,
          "Content-Type": "application/json"
        }
      });
      const busacrUser = await response.json();
      const respuestaSeguidores = await fetch(urlUser3+'/' + busacrUser.idUser + '/followers', {
        method: "GET",
        headers: {
          "Authorization": `Bearer ${token3}`,
          "Content-Type": "application/json"
        }
      });
      
      const respuestaSeguidos = await fetch(urlUser3+'/' + busacrUser.idUser + '/followed', {
        method: "GET",
        headers: {
          "Authorization": `Bearer ${token3}`,
          "Content-Type": "application/json"
        }
      });

      if (!respuestaSeguidos.ok) {
        alert("Este usuario no existe");
        throw new Error(`Error en la solicitud: ${response.status}`);
        
      }

      if (!respuestaSeguidores.ok) {
        alert("Este usuario no existe");
        throw new Error(`Error en la solicitud: ${response.status}`);
        
      }

      if (!response.ok) {
        alert("Este usuario no existe");
        throw new Error(`Error en la solicitud: ${response.status}`);
        
      }
      const Buscaralgo = await respuestaSeguidores.json();
      const Buscaralgo2 = await respuestaSeguidos.json();
      console.log(Buscaralgo)

      let encabezadoPublicacion = document.querySelector(".encabezado");
      let cuerpo = document.querySelector(".publicacion");
          encabezadoPublicacion.innerHTML = "";
          cuerpo.innerHTML="";
          encabezadoPublicacion.innerHTML = `
          <i class='bx bx-search-alt-2'></i>
            <input type="text" placeholder="Buscar en ParchApp" aria-label="Buscar publicaciones">`;
            cuerpo.innerHTML = `
              <div class="contenedorUsuario">
                <div class="usuarioImagen">
                    <img src="${busacrUser.urlPhoto}" alt="Imagen de perfil del usuario">
                </div>
                <div class="usuarionombre">
                  <div class="nombre">
                    <h2>@${busacrUser.nameUser}</h2>
                  <h2>${busacrUser.name}</h2>
                  </div>
                  
                  <div class="seguidores">
                    <h2>Seguidores:</h2>
                    <h2>Seguidos:</h2>
                  </div>
                  <div class="numero">
                    <h2>${Buscaralgo.length}</h2>
                    <h2>${Buscaralgo2.length}</h2>
                  </div>
                  <div class="bio">
                    <h1>${busacrUser.biography}</h1>
                  </div>
                  <div class="editPerfil">
                    <button class="hacerSeguir"> Seguir </button>
                  </div>
                </div>
              </div>
          `;
          let eventoSeguir = document.querySelector(".hacerSeguir")
          eventoSeguir.addEventListener("click", function(){
            hacerSeguimiento(busacrUser.idUser)
          })
    } catch (error) {
      console.error('Hubo un problema con la solicitud:', error);
    }
  
}

async function hacerSeguimiento(idUser) {
    let urlSeguimiento = "http://localhost:3002/api/follower";
  
    try {
      // Obtener todas las reacciones
      const respuesta = await fetch(urlSeguimiento, {
        method: "GET",
        headers: {
          "Authorization": `Bearer ${token3}`,
          "Content-Type": "application/json",
        },
      });
  
      if (!respuesta.ok) {
        console.error("Error al obtener las reacciones:", respuesta.status);
        return;
      }
  
      const datos2 = await respuesta.json();
  
      // Verificar si el usuario ya reaccionó a esta publicación
      const reaccionExistente = datos2.find(
        (element) => element.idUser == userId && element.idPost == postId
      );
  
      if (reaccionExistente) {
        // Si la reacción existe, eliminarla
        console.log("Eliminando reacción con ID:", reaccionExistente.idLike);
        const respuestaDelete = await fetch(urlSeguimiento+idUser, {
          method: "DELETE",
          headers: {
            "Authorization": `Bearer ${token3}`,
            "Content-Type": "application/json",
          },
        });
  
        if (respuestaDelete.ok) {
          console.log("Reacción eliminada");
          fetchData(); // Recargar datos
        } else {
          console.error("Error al eliminar la reacción:", respuestaDelete.status);
        }
      } else {
        // Si la reacción no existe, agregarla
        console.log("Agregando nueva reacción");
        const datosEnvioSeguimiento = {
            userFollowerId: id,
            userFollowedId: idUser,
        };
  
        const respuestaPost = await fetch(urlReaction, {
          method: "POST",
          headers: {
            "Authorization": `Bearer ${token3}`,
            "Content-Type": "application/json",
          },
          body: JSON.stringify(datosEnvioSeguimiento),
        });
  
        if (respuestaPost.ok) {
          console.log("Seguimiento agregada");
          buscarPublicaciones(); // Recargar datos
        } else {
          console.error("Error al agregar la reacción:", respuestaPost.status);
        }
      }
    } catch (error) {
      console.error("Error de red:", error);
    }
  }