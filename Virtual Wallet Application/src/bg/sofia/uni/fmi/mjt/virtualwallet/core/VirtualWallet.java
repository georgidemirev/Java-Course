package bg.sofia.uni.fmi.mjt.virtualwallet.core;

import bg.sofia.uni.fmi.mjt.virtualwallet.core.card.Card;
import bg.sofia.uni.fmi.mjt.virtualwallet.core.payment.PaymentInfo;
import bg.sofia.uni.fmi.mjt.virtualwallet.core.payment.Transaction;

import java.time.LocalDateTime;
import java.util.*;

public class VirtualWallet implements VirtualWalletAPI {

    private static final int MAX_TRANSACTIONS = 10;
    private static final int MAX_CARDS = 5;


    private Set<Card> cards = new HashSet<>();
    private List<PaymentInfo> payments = new ArrayList<>();
    private Deque<Transaction> transactions = new ArrayDeque<>();


    public VirtualWallet() {

    }

    @Override
    public boolean registerCard(Card card) {
        if (cards.contains(card) || card == null || cards.size() >= MAX_CARDS) {
            return false;
        } else {
            cards.add(card);
        }
        return true;
    }

    @Override
    public boolean executePayment(Card card, PaymentInfo paymentInfo) {
        if (card == null || card.getName() == null || paymentInfo == null || paymentInfo.getCost() < 0) {
            return false;
        } else {
            boolean exists = false;
            for (Card i : cards) {
                if (i.getName().equals(card.getName())) {
                    i.executePayment(paymentInfo.getCost());
                    exists = true;
                    break;
                }
            }
            if (!exists) {
                return false;
            }
            payments.add(paymentInfo);
            if (transactions.size() == MAX_TRANSACTIONS) {
                transactions.pop();
            }
            LocalDateTime dateNow = LocalDateTime.now();
            transactions.push(new Transaction(card.getName(), dateNow, paymentInfo));
            return true;
        }
    }

    @Override
    public boolean feed(Card card, double amount) {
        if (card == null || card.getName() == null || amount < 0) {
            return false;
        } else {
            boolean exists = false;
            for (Card i : cards) {
                if (i.getName().equals(card.getName())) {
                    i.setAmount(i.getAmount() + amount);
                    exists = true;
                    break;
                }
            }
            return exists;
        }
    }

    @Override
    public Card getCardByName(String name) {
        if (name == null) {
            return null;
        } else {
            for (Card i : cards) {
                if (i.getName().equals(name)) {
                    return i;
                }
            }
        }
        return null;
    }

    @Override
    public int getTotalNumberOfCards() {
        return cards.size();
    }
}
