package edu.ntnu.idi.idatt2003.oving5;

import javafx.scene.layout.HBox;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.embed.swing.SwingFXUtils;
import java.awt.image.BufferedImage;
import java.awt.Graphics2D;
import java.net.URI;
import java.net.URL;
import java.util.List;
import com.kitfox.svg.SVGUniverse;
import com.kitfox.svg.SVGDiagram;

public class CardDisplay {
    private final SVGUniverse svgUniverse = new SVGUniverse();
    private final HBox deckDisplay;
    private final Label handLabel;

    public CardDisplay(HBox deckDisplay, Label handLabel) {
        this.deckDisplay = deckDisplay;
        this.handLabel = handLabel;
    }

    public void showHand(List<PlayingCard> cards) {
        deckDisplay.getChildren().clear();
        HandOfCards hand = new HandOfCards(cards);
        handLabel.setText(hand.evaluateHand());

        for (PlayingCard card : cards) {
            try {
                String filePath = "/cards/" + faceToName(card.getFace()) + "_of_" + suitToName(card.getSuit()) + ".svg";
                URL url = getClass().getResource(filePath);
                if (url == null) {
                    System.out.println("File not found: " + filePath);
                    continue;
                }

                URI uri = svgUniverse.loadSVG(url);
                SVGDiagram diagram = svgUniverse.getDiagram(uri);

                int width = Math.max(1, (int) diagram.getWidth());
                int height = Math.max(1, (int) diagram.getHeight());
                BufferedImage bufferedImage = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);

                Graphics2D g2d = bufferedImage.createGraphics();
                diagram.render(g2d);
                g2d.dispose();

                ImageView imageView = new ImageView(SwingFXUtils.toFXImage(bufferedImage, null));
                imageView.setFitHeight(150);
                imageView.setPreserveRatio(true);
                deckDisplay.getChildren().add(imageView);

            } catch (Exception ex) {
                ex.printStackTrace();
            }
        }
    }

    private String faceToName(int face) {
        return switch (face) {
            case 1 -> "ace";
            case 11 -> "jack";
            case 12 -> "queen";
            case 13 -> "king";
            default -> String.valueOf(face);
        };
    }

    private String suitToName(char suit) {
        return switch (suit) {
            case 'S' -> "spades";
            case 'H' -> "hearts";
            case 'D' -> "diamonds";
            case 'C' -> "clubs";
            default -> throw new IllegalArgumentException("Invalid suit: " + suit);
        };
    }
}