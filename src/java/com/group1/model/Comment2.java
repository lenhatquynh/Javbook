/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.group1.model;

import com.group1.misc.Secret;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

/**
 *
 * @author Mr Khang
 */
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class Comment2 {

    private String userName;
    private String userImage;
    private int comment2Id, commentId;
    private String text;
    private String time;
    private int update;
    private int numberOfEmoji;
    private String max1, max2;
    private int userEmotion;
    private int userID;

    public String getID() {
        return Secret.encode2(String.valueOf(userID));
    }
}
