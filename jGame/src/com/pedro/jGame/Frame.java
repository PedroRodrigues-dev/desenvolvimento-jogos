package com.pedro.jGame;

import java.awt.Canvas;

import javax.swing.JFrame;

public class Frame extends Canvas{
	
	private JFrame frame;
	
	public Frame() {
		
	}
	
	private void createFrame() {
		this.frame = new JFrame("Pokemon Fire Red");

		this.frame.add(this);
		this.frame.setResizable(false);
		this.frame.pack();

		this.frame.setLocationRelativeTo(null);
		this.frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.frame.setVisible(true);
	}
}
