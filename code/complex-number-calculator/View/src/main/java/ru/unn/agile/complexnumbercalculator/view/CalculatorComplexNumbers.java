package ru.unn.agile.complexnumbercalculator.view;

import ru.unn.agile.complexnumbercalculator.viewmodel.ViewModel;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class CalculatorComplexNumbers {
    private JPanel mainPanel;
    private JPanel chooseOperationPanel;
    private JPanel enterDataPanel;
    private JPanel calculationPanel;
    private JLabel chooseLabel;
    private JComboBox operationsComboBox;
    private JLabel enterDataLabel;
    private JLabel resultLabel;
    private JButton calculateButton;
    private JTextField resultTextField;
    private JTextField firstReTextField;
    private JTextField firstImTextField;
    private JTextField secondReTextField;
    private JTextField secondImTextField;
    private JTextField degeeTextField;
    private JLabel fisrtReLabel;
    private JLabel fisrtImLabel;
    private JLabel secondReLabel;
    private JLabel secondImLabel;
    private JLabel degreeLabel;
    private JPanel errorPanel;
    private JLabel errorLabel;

    private ViewModel viewModel;

    public static void main(final String[] args) {
        JFrame frame = new JFrame("CalculatorComplexNumbers");
        frame.setContentPane(new CalculatorComplexNumbers(new ViewModel()).mainPanel);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
        frame.setResizable(false);
    }

    private CalculatorComplexNumbers(final ViewModel viewModel){
        this.viewModel = viewModel;
        backBind();
        loadOperations();
        calculateButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                bind();
                CalculatorComplexNumbers.this.viewModel.calculate();
                backBind();
            }
        });

        KeyAdapter keyListener = new KeyAdapter() {
            public void keyReleased(final KeyEvent e) {
                bind();
                CalculatorComplexNumbers.this.viewModel.processTextFieldFilling(e.getKeyCode());
                backBind();
            }
        };
        firstReTextField.addKeyListener(keyListener);
        firstImTextField.addKeyListener(keyListener);
        secondReTextField.addKeyListener(keyListener);
        secondImTextField.addKeyListener(keyListener);
    }

    private void loadOperations(){
        ViewModel.Operations[] operations = ViewModel.Operations.values();
        operationsComboBox.setModel(new JComboBox<>(operations).getModel());
    }

    private void backBind(){
        calculateButton.setEnabled(viewModel.isCalculateButtonEnabled());
        resultTextField.setText(viewModel.getResult());
    }

    private void bind(){
        viewModel.setFirstRe(firstReTextField.getText());
        viewModel.setFirstIm(firstImTextField.getText());
        viewModel.setSecondRe(secondReTextField.getText());
        viewModel.setSecondIm(secondImTextField.getText());


    }

}