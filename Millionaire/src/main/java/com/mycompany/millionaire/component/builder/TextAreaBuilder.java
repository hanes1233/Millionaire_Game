/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.mycompany.millionaire.component.builder;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import javax.swing.JTextArea;
import javax.swing.border.Border;

/**
 *
 * @author pavel
 */
public interface TextAreaBuilder extends Builder{
    
    @Override
    TextAreaBuilder text(String text);
    
    @Override
    TextAreaBuilder background(Color color);
    
    @Override
    TextAreaBuilder foreground(Color color);
    
    @Override
    TextAreaBuilder bounds(int x, int y, int width, int height);
    
    @Override
    TextAreaBuilder font(Font font);
    
    @Override
    TextAreaBuilder size(Dimension d);
    
    @Override
    TextAreaBuilder size(int width, int height);
    
    @Override
    TextAreaBuilder border(Border border);
    
    @Override
    JTextArea get();
    
    TextAreaBuilder formatText();
    
    TextAreaBuilder readOnly();
}
