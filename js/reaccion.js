
async function hacerLikePerfil(postId) {
    let urlReaction = "http://localhost:3002/api/reaction";
    let token = localStorage.getItem("token");
    let userId = localStorage.getItem("id");
  
    try {
      // Obtener todas las reacciones
      const respuesta = await fetch(urlReaction, {
        method: "GET",
        headers: {
          "Authorization": `Bearer ${token}`,
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
        const respuestaDelete = await fetch(`${urlReaction}/${reaccionExistente.idLike}`, {
          method: "DELETE",
          headers: {
            "Authorization": `Bearer ${token}`,
            "Content-Type": "application/json",
          },
        });
  
        if (respuestaDelete.ok) {
          console.log("Reacción eliminada");
          mostrarPerfil(); // Recargar datos
        } else {
          console.error("Error al eliminar la reacción:", respuestaDelete.status);
        }
      } else {
        // Si la reacción no existe, agregarla
        console.log("Agregando nueva reacción");
        const datosEnvioReaction = {
          idUser: userId,
          idPost: postId,
        };
  
        const respuestaPost = await fetch(urlReaction, {
          method: "POST",
          headers: {
            "Authorization": `Bearer ${token}`,
            "Content-Type": "application/json",
          },
          body: JSON.stringify(datosEnvioReaction),
        });
  
        if (respuestaPost.ok) {
          alert("Reacción agregada");
          mostrarPerfil()
        } else {
          console.error("Error al agregar la reacción:", respuestaPost.status);
        }
      }
    } catch (error) {
      console.error("Error de red:", error);
    }
  }

  async function hacerLike(postId) {
    let urlReaction = "http://localhost:3002/api/reaction";
    let token = localStorage.getItem("token");
    let userId = localStorage.getItem("id");
  
    try {
      // Obtener todas las reacciones
      const respuesta = await fetch(urlReaction, {
        method: "GET",
        headers: {
          "Authorization": `Bearer ${token}`,
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
        const respuestaDelete = await fetch(`${urlReaction}/${reaccionExistente.idLike}`, {
          method: "DELETE",
          headers: {
            "Authorization": `Bearer ${token}`,
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
        const datosEnvioReaction = {
          idUser: userId,
          idPost: postId,
        };
  
        const respuestaPost = await fetch(urlReaction, {
          method: "POST",
          headers: {
            "Authorization": `Bearer ${token}`,
            "Content-Type": "application/json",
          },
          body: JSON.stringify(datosEnvioReaction),
        });
  
        if (respuestaPost.ok) {
          console.log("Reacción agregada");
          fetchData(); // Recargar datos
        } else {
          console.error("Error al agregar la reacción:", respuestaPost.status);
        }
      }
    } catch (error) {
      console.error("Error de red:", error);
    }
  }