package campus.u2.parchap.user.domain;

import campus.u2.parchap.follower.domain.FollowerDTO;
import campus.u2.parchap.post.domain.PostDTO;
import java.util.List;

public class UserDTO {

    private Long idUser;
    private String name;
    private String nameUser;
    private String email;
    private String biography;
    private String urlPhoto;
    private String Password;
    private String token;
    private List<PostDTO> posts;
    private List<FollowerDTO> followers;
    private List<FollowerDTO> followered;

    public UserDTO() {
    }

    public UserDTO(Long idUser, String name, String nameUser, String email, String biography, String urlPhoto, String token, List<PostDTO> posts, List<FollowerDTO> followers, List<FollowerDTO> followered) {
        this.idUser = idUser;
        this.name = name;
        this.nameUser = nameUser;
        this.email = email;
        this.biography = biography;
        this.urlPhoto = urlPhoto;
        this.token = token;
        this.posts = posts;
        this.followers = followers;
        this.followered = followered;
    }

    public UserDTO(Long idUser, String name, String nameUser, String email, String biography, String urlPhoto) {
        this.idUser = idUser;
        this.name = name;
        this.nameUser = nameUser;
        this.email = email;
        this.biography = biography;
        this.urlPhoto = urlPhoto;
    }

    public UserDTO(String name, String nameUser, String email, String biography, String urlPhoto) {
        this.name = name;
        this.nameUser = nameUser;
        this.email = email;
        this.biography = biography;
        this.urlPhoto = urlPhoto;
    }

    public UserDTO(Long idUser, String name, String nameUser, String email, String biography, String urlPhoto, String token) {
        this.idUser = idUser;
        this.name = name;
        this.nameUser = nameUser;
        this.email = email;
        this.biography = biography;
        this.urlPhoto = urlPhoto;
        this.token = token;
    }

    public UserDTO(String name, String nameUser, String email, String biography, String urlPhoto, String token) {
        this.name = name;
        this.nameUser = nameUser;
        this.email = email;
        this.biography = biography;
        this.urlPhoto = urlPhoto;
        this.token = token;
    }

    public UserDTO(Long idUser, String name, String nameUser, String email, String biography, String urlPhoto, List<PostDTO> posts, List<FollowerDTO> followers, List<FollowerDTO> followered) {
        this.idUser = idUser;
        this.name = name;
        this.nameUser = nameUser;
        this.email = email;
        this.biography = biography;
        this.urlPhoto = urlPhoto;
        this.posts = posts;
        this.followers = followers;
        this.followered = followered;
    }

    public UserDTO(String name, String nameUser, String email, String biography, String urlPhoto, List<PostDTO> posts, List<FollowerDTO> followers, List<FollowerDTO> followered) {
        this.name = name;
        this.nameUser = nameUser;
        this.email = email;
        this.biography = biography;
        this.urlPhoto = urlPhoto;
        this.posts = posts;
        this.followers = followers;
        this.followered = followered;
    }

    public UserDTO(String errorMessage) {
        this.name = errorMessage;
        this.nameUser = "";
        this.email = "";
        this.biography = "";
        this.urlPhoto = "";
        this.token = "";
        this.posts = null;
        this.followers = null;
        this.followered = null;
    }

    public UserDTO(String nameUser, String email, String token) {
        this.nameUser = nameUser;
        this.email = email;
        this.token = token;
    }

    public Long getIdUser() {
        return idUser;
    }

    public void setIdUser(Long idUser) {
        this.idUser = idUser;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getNameUser() {
        return nameUser;
    }

    public void setNameUser(String nameUser) {
        this.nameUser = nameUser;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getBiography() {
        return biography;
    }

    public void setBiography(String biography) {
        this.biography = biography;
    }

    public String getUrlPhoto() {
        return urlPhoto;
    }

    public void setUrlPhoto(String urlPhoto) {
        this.urlPhoto = urlPhoto;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public List<PostDTO> getPosts() {
        return posts;
    }

    public void setPosts(List<PostDTO> posts) {
        this.posts = posts;
    }

    public List<FollowerDTO> getFollowers() {
        return followers;
    }

    public void setFollowers(List<FollowerDTO> followers) {
        this.followers = followers;
    }

    public List<FollowerDTO> getFollowered() {
        return followered;
    }

    public void setFollowered(List<FollowerDTO> followered) {
        this.followered = followered;
    }

    public String getPassword() {
        return Password;
    }

    public void setPassword(String Pasword) {
        this.Password = Pasword;
    }
    
    

}
