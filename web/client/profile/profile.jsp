<!DOCTYPE html>
<html lang="en">
  <head>
    <meta charset="UTF-8" />
    <meta http-equiv="X-UA-Compatible" content="IE=edge" />
    <meta name="viewport" content="width=device-width, initial-scale=1.0" />
    <title>Document</title>
    <script
      src="https://kit.fontawesome.com/466439df6f.js"
      crossorigin="anonymous"
    ></script>
    <link rel="stylesheet" href="./assets/css/Common/variables.css" />
    <link rel="stylesheet" href="./assets/css/Common/base.css" />
    <link rel="stylesheet" href="./assets/css/profile/style.css" />
    <script src="./assets/js/main.js" async></script>
  </head>
  <body>
    <div class="main-container">
      <div class="profile">
        <div class="profile-name-background"></div>
        <div class="profile-avatar">
          <img src="./assets/image/avatar.jpg" alt="" class="profile-img" />

          <div class="profile-name">Nguyễn Hoàng Khang</div>
        </div>
        <div class="edit-header-avatar">
          <label for="header-avatar-file">
            <i class="fas fa-camera"></i>
          </label>
          <input
            type="file"
            name="photo-file"
            id="header-avatar-file"
            accept="image/* "
          />
        </div>
        <div class="photo-profile-cover">
          <div class="edit-cover-img">
            <label for="header-photo-file">
              <i class="far fa-edit fa-lg"></i>
            </label>
            <input
              type="file"
              name="photo-file"
              id="header-photo-file"
              accept="image/* "
            />
          </div>
          <img
            src="https://scontent.fvca1-2.fna.fbcdn.net/v/t1.6435-9/153880781_1269206826834221_640873498255439976_n.jpg?_nc_cat=100&ccb=1-5&_nc_sid=e3f864&_nc_ohc=TSfn9YA190wAX-S5rLI&_nc_ht=scontent.fvca1-2.fna&oh=f93eb6f75dc683485e9368c55058ae26&oe=61721FE1"
            alt=""
            class="profile-cover"
          />
        </div>

        <div class="profile-menu">
          <a class="profile-menu-link active">Post</a>
          <a class="profile-menu-link">About</a>
          <a class="profile-menu-link">Friends</a>
          <a class="profile-menu-link">Photos</a>
        </div>
      </div>
      <div class="timeline"></div>
    </div>
  </body>
</html>
