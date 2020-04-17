package br.pro.hashi.ensino.desagil.aps.model;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.net.URL;

// Duas modificações em relação à versão da entrega anterior:
// (a) esta classe agora é subclasse de FixedPanel em vez
// de JPanel; e (b) esta classe agora implementa MouseListener,
// indicando que ela reage a eventos de interação com o mouse.
public class GateView extends JPanel implements ActionListener, MouseListener {
    private final Gate gate;

    private final JTextField weightField;
    private final JTextField radiusField;
    private final JTextField resultField;
    private final Image image;
    // Novos atributos necessários para esta versão da interface.
    private Color color;

    public GateView(int width, int height, Gate gate) {

        // É muito importante estabelecer que esse painel
        // não tem layout, pois caso contrário seu tamanho
        // vai ser definido pelo tamanho de seu conteúdo.
        setLayout(null);

        // Usamos esse método nos Desafios, vocês lembram?
        setPreferredSize(new Dimension(width, height));

        this.gate = gate;

        weightField = new JTextField();
        radiusField = new JTextField();
        resultField = new JTextField();

        JLabel weightLabel = new JLabel("Weight");
        JLabel radiusLabel = new JLabel("Radius");
        JLabel resultLabel = new JLabel("Result");

        // Não há mais a chamada de setLayout, pois ela agora
        // acontece no construtor da superclasse FixedPanel.

        // Como subclasse de FixedPanel, agora podemos definir a
        // posição e o tamanho de cada componente ao adicioná-la.
        add(weightLabel, 10, 10, 75, 25);
        add(weightField, 85, 10, 150, 25);
        add(radiusLabel, 10, 45, 75, 25);
        add(radiusField, 85, 45, 150, 25);
        add(resultLabel, 10, 311, 75, 25);
        add(resultField, 85, 311, 120, 25);

        // Inicializamos o atributo de cor simplesmente como preto.
        color = Color.BLACK;

        // Usamos esse carregamento nos Desafios, vocês lembram?
        String name = gate.toString() + ".png";
        URL url = getClass().getClassLoader().getResource(name);
        image = getToolkit().getImage(url);

        weightField.addActionListener(this);
        radiusField.addActionListener(this);

        resultField.setEnabled(false);

        // Toda componente Swing tem uma lista de observadores
        // que reagem quando algum evento de mouse acontece.
        // Usamos o método addMouseListener para adicionar a
        // própria componente, ou seja "this", nessa lista.
        // Só que addMouseListener espera receber um objeto
        // do tipo MouseListener como parâmetro. É por isso que
        // adicionamos o "implements MouseListener" lá em cima.
        addMouseListener(this);

        update();
    }

    // Sobrecarga do método add que permite definir posição
    // e tamanho de cada componente adicionada ao painel.
    // Fixar posição e tamanho também não é uma boa prática,
    // pois o normal é deixar o layout decidir. Novamente,
    // escolhemos fazer isso aqui para priorizar simplicidade.
    protected Component add(Component comp, int x, int y, int width, int height) {

        // Usa a implementação original para adicionar.
        super.add(comp);

        // Redefine posição e tamanho da componente.
        comp.setBounds(x, y, width, height);

        return comp;
    }

    private void update() {
        double weight;
        double radius;

        try {
            weight = Double.parseDouble(weightField.getText());
            radius = Double.parseDouble(radiusField.getText());
        } catch (NumberFormatException exception) {
            resultField.setText("?");
            return;
        }

        double result = gate.calculate(weight, radius);

        resultField.setText(Double.toString(result));
    }

    @Override
    public void actionPerformed(ActionEvent event) {
        update();
    }

    @Override
    public void mouseClicked(MouseEvent event) {

        // Descobre em qual posição o clique ocorreu.
        int x = event.getX();
        int y = event.getY();

        // Se o clique foi dentro do quadrado colorido...
        if (x >= 210 && x < 235 && y >= 311 && y < 336) {

            // ...então abrimos a janela seletora de cor...
            color = JColorChooser.showDialog(this, null, color);

            // ...e chamamos repaint para atualizar a tela.
            repaint();
        }
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

        // Não podemos esquecer desta linha, pois não somos os
        // únicos responsáveis por desenhar o painel, como era
        // o caso nos Desafios. Agora é preciso desenhar também
        // componentes internas, e isso é feito pela superclasse.
        super.paintComponent(g);

        // Desenha a imagem, passando sua posição e seu tamanho.
        g.drawImage(image, 10, 80, 221, 221, this);

        // Desenha um quadrado cheio.
        g.setColor(color);
        g.fillRect(210, 311, 25, 25);

        // Linha necessária para evitar atrasos
        // de renderização em sistemas Linux.
        getToolkit().sync();
    }
}

