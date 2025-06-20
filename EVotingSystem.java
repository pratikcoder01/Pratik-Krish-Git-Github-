import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class EVotingSystem extends JFrame implements ActionListener {
    private JButton[] voteButtons = new JButton[3];
    private JLabel[] voteLabels = new JLabel[3];
    private int[] votes = new int[3];
    private String[] candidates = {"Candidate A", "Candidate B", "Candidate C"};

    public EVotingSystem() {
        setTitle("E-Voting System");
        setSize(400, 400);
        setLocationRelativeTo(null);

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new GridLayout(5, 1)); 

        JLabel label = new JLabel("Welcome to the E-Voting System! Select your candidate:", JLabel.CENTER);
        add(label);

        for (int i = 0; i < 3; i++) {
            JPanel panel = new JPanel();
            voteButtons[i] = new JButton(candidates[i]);
            voteButtons[i].addActionListener(this);
            panel.add(voteButtons[i]);
            voteLabels[i] = new JLabel("Votes: 0");
            panel.add(voteLabels[i]);
            add(panel);
        }

        JButton resultButton = new JButton("Show Results");
        resultButton.addActionListener(e -> showResults());
        add(resultButton);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        for (int i = 0; i < 3; i++) {
            if (e.getSource() == voteButtons[i]) {
                votes[i]++;
                voteLabels[i].setText("Votes: " + votes[i]);
                JOptionPane.showMessageDialog(this, "Thank you for voting!");
            }
        }
    }

    private void showResults() {
        int maxVotes = -1, winnerIndex = -1;
        boolean tie = false;

        for (int i = 0; i < votes.length; i++) {
            if (votes[i] > maxVotes) {
                maxVotes = votes[i];
                winnerIndex = i;
                tie = false;
            } else if (votes[i] == maxVotes) {
                tie = true;
            }
        }

        String message = "Voting Results:\n";
        for (int i = 0; i < candidates.length; i++) {
            message += candidates[i] + ": " + votes[i] + " votes\n";
        }

        if (tie) {
            message += "It's a tie!";
        } else {
            message += "The winner is: " + candidates[winnerIndex];
        }

        JOptionPane.showMessageDialog(this, message);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            EVotingSystem frame = new EVotingSystem();
            frame.setVisible(true);
        });
    }
}