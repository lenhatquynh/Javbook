/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.group1.controller.processor;

import com.group1.misc.Pair;
import com.group1.model.Comment;
import com.group1.model.Comment2;
import com.group1.model.Post;
import com.group1.model.ProfileStatus;
import com.group1.model.ProfileUserAbout;
import com.group1.model.Status;
import com.group1.model.dao.CommentDAO;
import com.group1.model.dao.EmojiDAO;
import com.group1.model.dao.ProfileStatusDAO;
import com.group1.model.dao.ProfileUserAboutDAO;
import com.group1.model.dao.StatusDAO;
import com.group1.rest.BaseProcessor;
import com.group1.rest.ServeAt;
import com.group1.rest.ServeMethod;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author Mr Khang
 */
@WebServlet("/process/status/*")
public class StatusProcessor extends BaseProcessor {

    @ServeAt(value = "/crateStatus", method = ServeMethod.POST)
    public void serveCreateStatus(HttpServletRequest request, HttpServletResponse response) throws SQLException {
        try {
            String content = request.getParameter("content");
            String link = request.getParameter("link");
            String audience = request.getParameter("audience");
            int accountId = (int) request.getSession().getAttribute("uid");
            response.getWriter().print(StatusDAO.createStatus(accountId, content, link, audience));
        } catch (IOException ex) {
            System.out.println("Create Status Erorr");
        }

    }

    @ServeAt(value = "/editStatus", method = ServeMethod.POST)
    public void serveEditStatus(HttpServletRequest request, HttpServletResponse response) throws SQLException {
        String text = request.getParameter("text");
        String mood = request.getParameter("mood");
        String statusId = request.getParameter("id");
        StatusDAO.editStatus(statusId, text, mood);
    }

    @ServeAt(value = "/deleteStatus", method = ServeMethod.POST)
    public void serveDeleteStatus(HttpServletRequest request, HttpServletResponse response) throws SQLException {
        String statusId = request.getParameter("id");
        StatusDAO.deleteStatus(statusId);
    }

    @ServeAt(value = "/createComment", method = ServeMethod.POST)
    public void serveCreateComment(HttpServletRequest request, HttpServletResponse response) throws SQLException {
        try {
            String statusId = request.getParameter("id");
            int accountId = (int) request.getSession().getAttribute("uid");
            String text = request.getParameter("text");
            response.getWriter().print(CommentDAO.createComment(statusId, accountId, text));

        } catch (IOException ex) {
            System.out.println("Save Comment Data Erorr");
        }

    }

    @ServeAt(value = "/createComment2", method = ServeMethod.POST)
    public void serveCreateComment2(HttpServletRequest request, HttpServletResponse response) throws SQLException {
        try {
            String commentId = request.getParameter("id");
            int accountId = (int) request.getSession().getAttribute("uid");
            String text = request.getParameter("text");
            response.getWriter().print(CommentDAO.createComment2(commentId, accountId, text));
        } catch (IOException ex) {
            System.out.println("Save Comment2 Data Erorr");
        }

    }

    @ServeAt(value = "/render", method = ServeMethod.GET)
    public void serveRenderPost(HttpServletRequest request, HttpServletResponse response) {
        try {
            // Canh
            HttpSession ses = request.getSession();
            int accountId = (Integer) ses.getAttribute("uid");

            ProfileUserAbout profileUser = ProfileUserAboutDAO.getProfileUser(accountId);

            int profileStatusID = profileUser.getProfileStatusID();
            // get Profile Status
            ProfileStatus profileStatus = ProfileStatusDAO.getProfileStatus(profileStatusID);

            request.setAttribute("profileUser", profileUser);
            request.setAttribute("profileStatus", profileStatus);

            // --------------------------
            List<Status> status = StatusDAO.getListStatusUser(accountId);
            List<Post> posts = new ArrayList<>();
            if (status != null) {
                Collections.sort(status, (Status a, Status b) -> b.getStatusId() - a.getStatusId());

                for (Status stt : status) {
                    stt.setNumberOfEmoji(EmojiDAO.getStatusNumberOfEmojis(stt.getStatusId()));
                    stt.setUserEmotion(EmojiDAO.isStatusUserEmojis(stt.getStatusId(), accountId));
                    if (EmojiDAO.getListStatusMaxEmojis(stt.getStatusId()) != null) {
                        stt.setMax1(EmojiDAO.getListStatusMaxEmojis(stt.getStatusId()).get(0));
                        if (EmojiDAO.getListStatusMaxEmojis(stt.getStatusId()).size() == 2) {
                            stt.setMax2(EmojiDAO.getListStatusMaxEmojis(stt.getStatusId()).get(1));
                        }
                    }
                    Post post = new Post();
                    post.setStatus(stt);
                    List<Pair<Comment, List<Comment2>>> li = new ArrayList<>();
                    List<Comment> comments = CommentDAO.getListComment(stt.getStatusId());

                    if (comments != null) {
                        Collections.sort(comments, (Comment a, Comment b) -> b.getCommentId() - a.getCommentId());
                        for (Comment comment : comments) {
                            comment.setNumberOfEmoji(EmojiDAO.getCommentNumberOfEmojis(comment.getCommentId()));
                            comment.setUserEmotion(EmojiDAO.isCommentUserEmojis(comment.getCommentId(), accountId));
                            if (EmojiDAO.getListCommentMaxEmojis(comment.getCommentId()) != null) {
                                comment.setMax1(EmojiDAO.getListCommentMaxEmojis(comment.getCommentId()).get(0));
                                if (EmojiDAO.getListCommentMaxEmojis(comment.getCommentId()).size() == 2) {
                                    comment.setMax2(EmojiDAO.getListCommentMaxEmojis(comment.getCommentId()).get(1));
                                }
                            }
                            List<Comment2> comments2 = CommentDAO.getListComment2(comment.getCommentId());
                            if (comments2 != null) {
                                Collections.sort(comments2, (Comment2 a, Comment2 b) -> b.getComment2Id() - a.getComment2Id());
                                for (Comment2 comment2 : comments2) {
                                    comment2.setUserEmotion(EmojiDAO.isComment2UserEmojis(comment2.getComment2Id(), accountId));
                                    comment2.setNumberOfEmoji(EmojiDAO.getComment2NumberOfEmojis(comment2.getComment2Id()));
                                    if (EmojiDAO.getListComment2MaxEmojis(comment2.getComment2Id()) != null) {
                                        comment2.setMax1(EmojiDAO.getListComment2MaxEmojis(comment2.getComment2Id()).get(0));
                                        if (EmojiDAO.getListComment2MaxEmojis(comment2.getComment2Id()).size() == 2) {
                                            comment2.setMax2(EmojiDAO.getListComment2MaxEmojis(comment2.getComment2Id()).get(1));
                                        }
                                    }
                                }
                            }
                            li.add(new Pair<>(comment, comments2));
                        }
                        post.setComment(li);
                    }
                    posts.add(post);
                }

            }
            request.setAttribute("posts", posts);
            request.setAttribute("avatar", StatusDAO.getAvatar(accountId));
            request.getRequestDispatcher("/client/profile/profilePost.jsp").forward(request, response);
        } catch (ServletException ex) {
            System.out.println("Render ServletException");
        } catch (IOException ex) {
            System.out.println("RenderI OException ");
        }

    }

    @ServeAt(value = "/getTopStatusEmoji", method = ServeMethod.GET)
    public void serveGetTopStatusEmoji(HttpServletRequest request, HttpServletResponse response) throws SQLException {
        try {
            String statusID = request.getParameter("id");
            List<String> list = new ArrayList<>();
            list.add(String.valueOf(EmojiDAO.getStatusNumberOfEmojis(Integer.parseInt(statusID))));
            List<String> top = EmojiDAO.getListStatusMaxEmojis(Integer.parseInt(statusID));
            if (top != null) {
                for (String string : top) {
                    list.add(string);
                }
            }
            response.getWriter().print(list);
        } catch (IOException ex) {
            System.out.println("Get Number and Top Status Emoji Erorr");
        }

    }

    @ServeAt(value = "/getTopCommentEmoji", method = ServeMethod.GET)
    public void serveGetTopCommentEmoji(HttpServletRequest request, HttpServletResponse response) throws SQLException {
        try {
            String commentID = request.getParameter("id");
            List<String> list = new ArrayList<>();
            list.add(String.valueOf(EmojiDAO.getCommentNumberOfEmojis(Integer.parseInt(commentID))));
            List<String> top = EmojiDAO.getListCommentMaxEmojis(Integer.parseInt(commentID));
            if (top != null) {
                for (String string : top) {
                    list.add(string);
                }
            }
            response.getWriter().print(list);
        } catch (IOException ex) {
            System.out.println("Get Number and Top Comment Emoji Erorr");
        }

    }

    @ServeAt(value = "/getTopComment2Emoji", method = ServeMethod.GET)
    public void serveGetTopComment2Emoji(HttpServletRequest request, HttpServletResponse response) throws SQLException {
        try {
            String comment2 = request.getParameter("id");
            List<String> list = new ArrayList<>();
            list.add(String.valueOf(EmojiDAO.getComment2NumberOfEmojis(Integer.parseInt(comment2))));
            List<String> top = EmojiDAO.getListComment2MaxEmojis(Integer.parseInt(comment2));
            if (top != null) {
                for (String string : top) {
                    list.add(string);
                }
            }
            response.getWriter().print(list);
        } catch (IOException ex) {
            System.out.println("Get Number and Top Comment2 Emoji Erorr");
        }

    }
}
