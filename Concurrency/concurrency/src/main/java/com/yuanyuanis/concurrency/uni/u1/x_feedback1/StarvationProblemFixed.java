package com.yuanyuanis.concurrency.uni.u1.x_feedback1;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.regex.Pattern;

public class StarvationProblemFixed {

	public static void main(String[] args) {
		JFrame frame = createFrame();

		JLabel jLabel = new JLabel();
		jLabel.setBounds(50, 150, 350, 40);

		final JTextField inputNumberText = new JTextField();
		inputNumberText.setBounds(50, 50, 150, 20);

		JButton submitjButton = new JButton("Submit");
		submitjButton.setBounds(50, 100, 100, 30);


		ProgressThread progressThread = new ProgressThread();
		JComponent progressBar = progressThread.getProgressComponent();
		progressBar.setBounds(50, 200, 250, 40);
		frame.add(progressBar);

		JButton cancelJButton = new JButton("Cancel");
		cancelJButton.setBounds(50, 250, 100, 30);
		cancelJButton.setVisible(false);

		cancelJButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				progressThread.stop();
			}
		});


		submitjButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				String text = inputNumberText.getText();

				if (isNumeric(text) && Integer.parseInt(text)>1 ) {
					jLabel.setText(inputNumberText.getText());
					progressThread.setNumber(Integer.parseInt(text));
					progressThread.start();
					cancelJButton.setVisible(true);
				}
				else {
					jLabel.setText("El texto '%s' es incorrecto, debes introducir un número >= 2".formatted(text));
					jLabel.setForeground(Color.red);
				}
			}
		});


		frame.add(cancelJButton);
		frame.add(jLabel);
		frame.add(submitjButton);
		frame.add(inputNumberText);
		frame.setSize(400, 400);
		frame.setLayout(null);
		frame.setVisible(true);
	}


	private static class ProgressThread extends Thread {
		JProgressBar progressBar;
		Integer number;
		Integer subtractNumber;


		ProgressThread() {
			progressBar = new JProgressBar();
			progressBar.setString(this.getName());
			progressBar.setStringPainted(true);
		}

		private void initNumeration() {
			subtractNumber = 1;
		}

		public void setNumber(Integer number){
			this.number = number;
		}

		JComponent getProgressComponent() {
			return progressBar;
		}

		@Override
		public void run() {

			int c = 100;
			while (true) {
					if (c <= 0) {
						c = 100;
					}
					progressBar.setValue(--c);
					
				try {
					Thread.currentThread().sleep(100);
				} catch (InterruptedException e) {
					throw new RuntimeException(e);
				}
			}
		}
	}


	/**
	 *  ------------ UTILITY METHODS -------------
	 */

	private static boolean isNumeric(String string) {
		// Checks if the provided string
		// is a numeric by applying a regular
		// expression on it.
		String regex = "[0-9]+[.]?[0-9]*";
		return Pattern.matches(regex, string);
	}

	private static JFrame createFrame() {
		JFrame frame = new JFrame("Fairness Demo");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setSize(new Dimension(500, 400));
		return frame;
	}
}
