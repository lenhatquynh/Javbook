
<%-- 
    Document   : profileheader
    Created on : Oct 11, 2021, 7:57:36 PM
    Author     : Mr Khang
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <meta charset="UTF-8" />
        <meta http-equiv="X-UA-Compatible" content="IE=edge" />
        <meta name="viewport" content="width=device-width, initial-scale=1.0" />
        <title>Profile</title>
        <link
            rel="icon"
            href="/Javbook/assets/img/logo/Javbook_black.png"
            type="image/gif"
            sizes="16x16"
            />
        <link rel="stylesheet" href="/Javbook/assets/css/common/variables.css" />
        <link rel="stylesheet" href="/Javbook/assets/css/common/base.css" />
        <link rel="stylesheet" href="/Javbook/assets/css/profile/profileHeader.css" />
        <link rel="stylesheet" href="/Javbook/assets/css/profile/profilePost.css" />
        <link rel="stylesheet" href="/Javbook/assets/css/common/postBox.css" />     
        <link rel="stylesheet" href="/Javbook/assets/css/profile/profilePhoto.css" />
        <link rel="stylesheet" href="/Javbook/assets/css/profile/profileAbout.css" />



        <link rel="stylesheet" href="/Javbook/assets/fonts/fontawesome-5.15.4/css/all.min.css" />
        <script src="/Javbook/assets/js/common/postBox.js" async></script>
        <script src="/Javbook/assets/js/common/query.js" async></script>
        <script src="/Javbook/assets/js/profile/profileHeader.js" async  ></script>
        <script src="/Javbook/assets/js/profile/profilePost.js" async></script>


    </head>
    <body>
        <div class="main-container">
            <div class="profile">
                <div class="profile-name-background"></div>
                <div class="profile-avatar">
                    <img src="/Javbook/assets/img/default/avatar.png" alt="" class="profile-img" />

                    <div class="profile-name">Javbook User </div>
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
                        src="/Javbook/assets/img/default/cover.jpg"
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
            <div class="timeline">
                <%@include file="profilePost.jsp" %>
            </div>
        </div>
        <!-- popup  -->
        <div class="popup_model flex_center popup-main-post">
            <div class="post_popup" id="post_popup">

                <div class="pop_ele1 flex_center">
                    <span class="pop_title">Create Post</span>
                    <span class="close pointer">+</span>
                </div>

                <div class="pop_ele2 flex">
                    <div class="wh_40">
                        <img
                            class="wh_40 scale circle"
                            src="/Javbook/assets/img/default/avatar.png"
                            alt=""
                            />
                    </div>
                    <div class="status_content flex_gr1">
                        <textarea

                            name="input_field"
                            cols=""
                            rows=""
                            class="enter"
                            placeholder="What's your mind?"
                            ></textarea>
                        <div class="display-img">
                            <img src="" alt="" id="status-img" />
                            <span class="close-img"><i class="fas fa-times"></i></span>
                        </div>
                    </div>
                </div>

                <div class="pop_ele3 flex">
                    <select name="object" id="select" class="popup_btn pointer">
                        <option value="1">Public</option>
                        <option value="2">Friends</option>
                        <option value="3">Only me</option>
                    </select>
                    <div class="input_file popup_btn pointer">
                        <input type="file" name="file" id="file" accept="image/*" />
                        <label for="file" class="photo pointer">
                            <i class="far fa-images"></i>
                            <span class="p_l5">Photo/Video</span>
                        </label>
                    </div>
                    <span class="share popup_btn pointer">Post</span>
                </div>
            </div>
        </div>
        <div class="popup_model flex_center popup-post-box">
            <div class="post_popup" id="post_popup">
                <div class="pop_ele1 flex_center">
                    <span class="pop_title">Edit Post</span>
                    <span class="close pointer">+</span>
                </div>

                <div class="pop_ele2 flex">
                    <div class="wh_40">
                        <img
                            class="wh_40 scale circle"
                            src="/Javbook/assets/img/default/avatar.png"
                            alt=""
                            />
                    </div>
                    <div class="status_content flex_gr1">
                        <textarea
                            name="input_field"
                            cols=""
                            rows=""
                            class="enter"
                            placeholder="What's your mind?"
                            ></textarea>
                        <div class="display-img">
                            <img src="" alt="" id="status-img" />
                        </div>
                    </div>
                </div>

                <div class="pop_ele3 flex">
                    <select name="object" id="select" class="popup_btn pointer">
                        <option value="public">Public</option>
                        <option value="friends">Friends</option>
                        <option value="only_me">Only me</option>
                    </select>

                    <span class="share popup_btn pointer">Save</span>
                </div>
            </div>
        </div>
    </body>

</html>