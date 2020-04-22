package br.pro.hashi.ensino.desagil.aps.view;

import br.pro.hashi.ensino.desagil.aps.model.*;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.net.URL;


public class GateView extends JPanel implements ActionListener, MouseListener {

    private final Gate gate;
    private final JCheckBox input0;
    private final JCheckBox input1;
    private final JCheckBox output;
    private final Switch zero;
    private final Switch one;
    private final Image image;

    public GateView(int width, int height, Gate gate) {
        setLayout(null);
        setPreferredSize(new Dimension(width, height));

        this.gate = gate;

        zero = new Switch();
        input0 = new JCheckBox();

        one = new Switch();
        input1 = new JCheckBox();

        output = new JCheckBox();

        if (gate.getInputSize() == 1) {
            gate.connect(0, zero);
        } else if (gate.getInputSize() == 2) {
            gate.connect(0, zero);
            gate.connect(1, one);
        }

        if (gate.getInputSize() == 1) {
            add(input0, 3, 164, 17, 13);
        } else if (gate.getInputSize() == 2) {
            add(input0, 3, 106, 17, 13);
            add(input1, 3, 221, 17, 13);
        }

        add(output, 222, 163, 17, 13);

        input0.addActionListener(this);
        input1.addActionListener(this);
        output.setEnabled(false);

        addMouseListener(this);
        if (gate.getInputSize() == 1) {
            updateOne();
        } else if (gate.getInputSize() == 2) {
            updateTwo();
        }

        // Usamos esse carregamento nos Desafios, vocês lembram?
        String name = gate.toString() + ".png";
        URL url = getClass().getClassLoader().getResource(name);
        image = getToolkit().getImage(url);

    }

    protected void add(Component comp, int x, int y, int width, int height) {
        super.add(comp);
        comp.setBounds(x, y, width, height);
    }

    private void updateOne() {
        boolean inputzero = Boolean.parseBoolean(null);
        boolean finaloutput;

        inputzero = input0.isSelected();

        finaloutput = connect(inputzero);
        output.setSelected(finaloutput);

    }

    private void updateTwo() {
        boolean inputzero;
        boolean inputone;
        boolean finaloutput;

        inputzero = input0.isSelected();
        inputone = input1.isSelected();

        finaloutput = connect(inputzero, inputone);
        output.setSelected(finaloutput);
    }

    public boolean connect(boolean inputzero) {
        if (inputzero) {
            zero.turnOn();
        } else {
            zero.turnOff();
        }

        return gate.read();
    }

    public boolean connect(boolean inputzero, boolean inputone) {
        if (inputzero) {
            zero.turnOn();
        } else {
            zero.turnOff();
        }
        if (inputone) {
            one.turnOn();
        } else {
            one.turnOff();
        }

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
        super.paintComponent(g);
        // Desenha a imagem, passando sua posição e seu tamanho.
        g.drawImage(image, 20, 60, 220, 220, this);
    }
}

