<%-- 
    Document   : profilepost
    Created on : Oct 11, 2021, 8:13:00 PM
    Author     : Mr Khang
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>




<div class="post-container" >
    <div class="timeline-left">
        <div class="intro box">
            <div class="intro-title">Introduction</div>
            <div class="info">
                <div class="info-item">
                    <i class="fas fa-briefcase"></i>
                    <p>
                        Studied Kỹ thuật phần mềm - Programing at
                        <a href="#">Đại Học FPT Cần Thơ</a>
                    </p>
                </div>
                <div class="info-item">
                    <i class="fas fa-home"></i>
                    <p>Live in <a href="#">Camau, Vietnam</a></p>
                </div>
                <div class="info-item">
                    <i class="fas fa-birthday-cake"></i>
                    <p>November 3, 2001</p>
                </div>
                <div class="info-item">
                    <i class="fas fa-transgender-alt"></i>
                    <p>Male</p>
                </div>
                <div class="info-item">
                    <i class="fas fa-heart"></i>
                    <p>Single</p>
                </div>
            </div>
        </div>
    </div>
    <!-- post container -->
    <div class="timeline-right">
        <div class="post_box flex_cl">
            <div class="post_ele1 flex flex_ac">
                <div class="wh_40">
                    <img
                        class="wh_40 scale circle"
                        src="./assets/image/avatar.jpg"
                        alt=""
                        />
                </div>

                <div class="input flex flex_ac pointer" id="input">
                    What's your mind?
                </div>
            </div>
            <div class="post_ele2 flex flex_ac">
                <div class="photo flex_gr1 p_l5 pointer hv">
                    <i class="far fa-images"></i>
                    <span class="p_l5">Photo/Video</span>
                </div>
                <div class="tagfriend flex_gr1 p_l5 pointer hv">
                    <i class="fas fa-user-tag"></i>
                    <span class="p_l5">Tag Friend</span>
                </div>
                <div class="feeling flex_gr1 p_l5 pointer hv">
                    <i class="far fa-grin"></i>
                    <span class="p_l5">Feeling</span>
                </div>
            </div>
        </div>

        <div class="list-post"></div>
    </div>
</div>



