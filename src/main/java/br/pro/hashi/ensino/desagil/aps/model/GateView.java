package br.pro.hashi.ensino.desagil.aps.model;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;


public class GateView extends JPanel implements ActionListener, MouseListener {

    private final Gate gate;
    private final JCheckBox input0;
    private final JCheckBox input1;
    private final JCheckBox output;
    private Switch zero;
    private Switch one;

    public GateView(int width, int height, Gate gate) {
        setLayout(null);
        setPreferredSize(new Dimension(width, height));

        this.gate = gate;

        zero = new Switch();
        input0= new JCheckBox();

        one = new Switch();
        input1 = new JCheckBox();

        output = new JCheckBox();

        JLabel input0Label = new JLabel("Input 0:");
        JLabel input1Label = new JLabel("Input 1:");
        JLabel outputLabel = new JLabel("Output:");

        add(input0Label, 93, 100, 75, 35);
        add(input0, 135, 100, 150, 35);

        if (gate.getInputSize() == 2) {
            add(input1Label, 93, 120, 75, 35);
            add(input1, 135, 120, 150, 35);
        }

        add(outputLabel, 93, 200, 75, 55);
        add(output, 135, 200, 120, 55);

        input0.addActionListener(this);
        input1.addActionListener(this);
        output.setEnabled(false);

        addMouseListener(this);
         if (gate.getInputSize() == 1) {
             updateOne();
         } else if (gate.getInputSize() == 2) {
             updateTwo();
        }

    }

    protected Component add(Component comp, int x, int y, int width, int height) {
        super.add(comp);
        comp.setBounds(x, y, width, height);
        return comp;
    }

    private void updateOne() {
        boolean inputzero = Boolean.parseBoolean(null);
        boolean finaloutput;

        try {
            inputzero = input0.isSelected();

        } finally {
            finaloutput = connect(inputzero);
            output.setSelected(finaloutput);
        }
    }

    private void updateTwo() {
        boolean inputzero = Boolean.parseBoolean(null);
        boolean inputone = Boolean.parseBoolean(null);
        boolean finaloutput;

        try {
            inputzero = input0.isSelected();
            inputone = input1.isSelected();
        } finally {
            finaloutput = connect(inputzero, inputone);
            output.setSelected(finaloutput);
        }
    }

    public boolean connect (boolean inputzero) {
        if (inputzero) {
            zero.turnOn();
        }
        else {
            zero.turnOff();
        }

        gate.connect(0, zero);

        return gate.read();
    }

    public boolean connect (boolean inputzero, boolean inputone) {
        if (inputzero) {
            zero.turnOn();
        }
        else {
            zero.turnOff();
        }
        if (inputone) {
            one.turnOn();
        }
        else {
            one.turnOff();
        }

        gate.connect(0, zero);
        gate.connect(1, one);

        return gate.read();
    }


    @Override
    public void actionPerformed(ActionEvent event) {
        if (gate.getInputSize() == 1) {
            updateOne();
        } else if (gate.getInputSize() == 2) {
            updateTwo();
        }
    }

    @Override
    public void mouseClicked(MouseEvent event) {
        // Não precisamos de uma reação específica à ação de pressionar
        // um botão do mouse, mas o contrato com MouseListener obriga
        // esse método a existir, então simplesmente deixamos vazio.
    }

    @Override
    public void mousePressed(MouseEvent event) {
        // Não precisamos de uma reação específica à ação de pressionar
        // um botão do mouse, mas o contrato com MouseListener obriga
        // esse método a existir, então simplesmente deixamos vazio.
    }

    @Override
    public void mouseReleased(MouseEvent event) {
        // Não precisamos de uma reação específica à ação de soltar
        // um botão do mouse, mas o contrato com MouseListener obriga
        // esse método a existir, então simplesmente deixamos vazio.
    }

    @Override
    public void mouseEntered(MouseEvent event) {
        // Não precisamos de uma reação específica à ação do mouse
        // entrar no painel, mas o contrato com MouseListener obriga
        // esse método a existir, então simplesmente deixamos vazio.
    }

    @Override
    public void mouseExited(MouseEvent event) {
        // Não precisamos de uma reação específica à ação do mouse
        // sair do painel, mas o contrato com MouseListener obriga
        // esse método a existir, então simplesmente deixamos vazio.
    }

    @Override
    public void paintComponent(Graphics g) {
        // Não precisamos de uma reação específica à ação do mouse
        // entrar no painel, mas o contrato com MouseListener obriga
        // esse método a existir, então simplesmente deixamos vazio.
    }
}

