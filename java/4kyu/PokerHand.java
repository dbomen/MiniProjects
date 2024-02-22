import java.util.*;

// import javax.print.attribute.HashDocAttributeSet;

public class PokerHand implements Comparable<PokerHand> {
    protected static List<String> listOfValues = List.of("A", "K", "Q", "J", "T", "9", "8", "7", "6", "5", "4", "3", "2");
    HandValue handValue;
    List<List<String>> hand;
    

    public PokerHand(String hand) {
        this.hand = List.of(getCardValuesOrSuits(hand, 0), getCardValuesOrSuits(hand, 1));
        this.handValue = getHandValue(this.hand);
    }

    public List<String> getCardValuesOrSuits(String hand, int i) {
        String[] hands = hand.split(" ");
        List<String> list = new ArrayList<>();

        for (String card : hands) {
            list.add(String.valueOf(card.charAt(i)));
        }

        return list;
    }

    public HandValue getHandValue(List<List<String>> hand) {
        if (straight(hand) && flush(hand)) {
            return new StraightFlush(hand);
        } else if (oK(hand) == 4) {
            return new FourOK(hand);
        } else if (oK(hand) == 3 && pair(hand, true) >= 1) {
            return new FullHouse(hand);
        } else if (flush(hand)) {
            return new Flush(hand);
        } else if (straight(hand)) {
            return new Straight(hand);
        } else if (oK(hand) == 3) {
            return new ThreeOK(hand);
        } else if (pair(hand, false) >= 2) {
            return new TwoPair(hand);
        } else if (pair(hand, false) >= 1) {
            return new Pair(hand);
        } else {
            return new HighCard(hand);
        }
    }

    public int compareTo(PokerHand other) {
        return (this.handValue.getValue() == other.handValue.getValue()) ? this.handValue.compareTo(other.handValue) : other.handValue.getValue() - this.handValue.getValue();
    }

    public int pair(List<List<String>> hand, boolean lookingForFullHouse) {
        Map<String, Integer> map = new HashMap<>();
        int numOfPairs = 0;

        for (String card : hand.get(0)) {
            if (map.containsKey(card)) {
                int nValue = map.get(card) + 1;
                map.put(card, nValue);
            } else {
                map.put(card, 1);
            }
        }

        for (String s : map.keySet()) {
            if (!lookingForFullHouse && map.get(s) >= 2) {
                numOfPairs++;
            } else if (lookingForFullHouse && map.get(s) == 2) {
                numOfPairs++;
            }
        }

        return numOfPairs;
    }

    public boolean flush(List<List<String>> hand) {
        String checker = hand.get(1).get(0);
        for (String card : hand.get(1)) {
            if (!card.equals(checker)) {
                return false;
            }
        }
        return true;
    }

    public boolean straight(List<List<String>> hand) {
        List<Integer> indexs = new ArrayList<>();

        for (String card : hand.get(0)) {
            indexs.add(listOfValues.indexOf(card));
        }

        Collections.sort(indexs);

        Integer before = indexs.get(0);

        for (int i = 1; i < indexs.size(); i++) {
            Integer current = indexs.get(i);
            if (!(before + 1 == current) && !(before == 0 && current == 9)) {
                return false;
            }
            before = current;
        }
        return true;
    }

    public int oK(List<List<String>> hand) {
        Map<String, Integer> map = new HashMap<>();
        int maxOfSame = 0;

        for (String card : hand.get(0)) {
            if (map.containsKey(card)) {
                int nValue = map.get(card) + 1;
                map.put(card, nValue);
            } else {
                map.put(card, 1);
            }
        }

        for (String s : map.keySet()) {
            if (map.get(s) > maxOfSame) {
                maxOfSame = map.get(s);
            }
        }

        return maxOfSame;
    }
    
    public static abstract class HandValue implements Comparable<HandValue> {
        protected int value;
        protected List<List<String>> hand;

        public HandValue(int value, List<List<String>> hand) {
            this.value = value;
            this.hand = hand;
        }

        public int getValue() {
            return this.value;
        }

        public abstract List<Integer> characteristic();

        public int compareTo(HandValue other) {
            for (int i = 0; i < this.characteristic().size(); i++) {
                if (!(this.characteristic().get(i) == other.characteristic().get(i))) {
                    return this.characteristic().get(i) - other.characteristic().get(i);
                }
            }
            return 0;
        }

        public List<Integer> getHighCards() {
            List<Integer> list = new ArrayList<>();
            for (String card : hand.get(0)) {
                list.add(listOfValues.indexOf(card));
            }

            Collections.sort(list);

            return list;
        }

        public List<Integer> getPair(int i) {
            List<Integer> pairs = new ArrayList<>();
            Map<String, Integer> map = new HashMap<>();

            for (String card : hand.get(0)) {
                if (map.containsKey(card)) {
                    int nValue = map.get(card) + 1;
                    map.put(card, nValue);
                } else {
                    map.put(card, 1);
                }
            }

            for (String s : map.keySet()) {
                if (map.get(s) >= i) {
                    pairs.add(listOfValues.indexOf(s));
                }
            }

            pairs.sort((a, b) -> map.get(listOfValues.get(b)) - map.get(listOfValues.get(a)));

            return pairs;
        }
    }

    private static class StraightFlush extends HandValue {

        public StraightFlush(List<List<String>> hand) {
            super(9, hand);
        }

        @Override
        public List<Integer> characteristic() {
            List<Integer> highCards = getHighCards();
            if (highCards.get(0) == 0 && this.hand.get(0).contains("4")) {
                return List.of(9, 10, 11, 12, 13);
            }
            return highCards;
        }
    }

    private static class FourOK extends HandValue {

        public FourOK(List<List<String>> hand) {
            super(8, hand);
        }

        @Override
        public List<Integer> characteristic() {
            List<Integer> list = new ArrayList<>(getPair(4));
            list.addAll(getHighCards());
            return list;
        }
    }

    private static class FullHouse extends HandValue {

        public FullHouse(List<List<String>> hand) {
            super(7, hand);
        }

        @Override
        public List<Integer> characteristic() {
            List<Integer> list = new ArrayList<>(getPair(3));
            list.addAll(getPair(2));
            list.addAll(getHighCards());
            return list;
        }
    }

    private static class Flush extends HandValue {

        public Flush(List<List<String>> hand) {
            super(6, hand);
        }

        @Override
        public List<Integer> characteristic() {
            return getHighCards();
        }
    }

    private static class Straight extends HandValue {

        public Straight(List<List<String>> hand) {
            super(5, hand);
        }

        @Override
        public List<Integer> characteristic() {
            List<Integer> highCards = getHighCards();
            if (highCards.get(0) == 0 && this.hand.get(0).contains("4")) {
                return List.of(9, 10, 11, 12, 13);
            }
            return highCards;
        }
    }

    private static class ThreeOK extends HandValue {

        public ThreeOK(List<List<String>> hand) {
            super(4, hand);
        }

        @Override
        public List<Integer> characteristic() {
            List<Integer> list = new ArrayList<>(getPair(3));
            list.addAll(getHighCards());
            return list;
        }
    }

    private static class TwoPair extends HandValue {

        public TwoPair(List<List<String>> hand) {
            super(3, hand);
        }

        @Override
        public List<Integer> characteristic() {
            List<Integer> list = new ArrayList<>(getPair(2));
            list.addAll(getHighCards());
            return list;
        }
    }

    private static class Pair extends HandValue {
        
        public Pair(List<List<String>> hand) {
            super(2, hand);
        }

        @Override
        public List<Integer> characteristic() {
            List<Integer> list = new ArrayList<>(getPair(2));
            list.addAll(getHighCards());
            return list;
        }
    }

    private static class HighCard extends HandValue {
        
        public HighCard(List<List<String>> hand) {
            super(1, hand);
        }

        @Override
        public List<Integer> characteristic() {
            return getHighCards();
        }
    }
}