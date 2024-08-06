package a04.e1;

import java.util.function.BiPredicate;
import java.util.function.Predicate;
import java.util.function.UnaryOperator;

//FINITO
public class BankAccountFactoryImpl implements BankAccountFactory {

    @Override // FATTO DA SOLAAAAA
    public BankAccount createBasic() {
        return new BankAccount() {

            private int balance;

            @Override
            public int getBalance() {
                return balance;
            }

            @Override
            public void deposit(int amount) {
                balance += amount;
            }

            @Override
            public boolean withdraw(int amount) {
                if (balance >= amount) {
                    balance -= amount;
                    return true;
                }
                return false;
            }

        };
    }

    @Override
    public BankAccount createWithFee(UnaryOperator<Integer> feeFunction) {
        return new BankAccount() {
            // FATTO DA SOLAAAAA
            private int balance = 0;

            @Override
            public int getBalance() {
                return balance;
            }

            @Override
            public void deposit(int amount) {
                balance += amount;
            }

            @Override
            public boolean withdraw(int amount) {
                if (balance >= amount + 1) {
                    if (amount > 35) {
                        // amount = amount + 1;
                        balance -= (amount + feeFunction.apply(amount));

                    } else {
                        balance -= amount;
                    }
                    return true;
                }
                return false;
            }

        };
    }

    @Override
    public BankAccount createWithCredit(Predicate<Integer> allowedCredit, UnaryOperator<Integer> rateFunction) {
        return new BankAccount() {

            private int balance = 0;

            @Override
            public int getBalance() {
                return balance;
            }

            @Override
            public void deposit(int amount) {
                balance += amount;
            }

            @Override
            public boolean withdraw(int amount) {
                var credit = balance - (amount);
                if (balance >= amount) {
                    balance -= amount;
                    return true;
                }
                if (allowedCredit.test(Math.abs(credit))) {
                    balance = credit - rateFunction.apply(credit); // mettere apply di credit o di amount non cambia
                    return true;
                }
                return false;
            }

        };
    }

    @Override
    public BankAccount createWithBlock(BiPredicate<Integer, Integer> blockingPolicy) {
        return new BankAccount() {

            private int balance = 0;
            boolean notBlocked = true;

            @Override
            public int getBalance() {
                return balance;
            }

            @Override
            public void deposit(int amount) {
                balance += amount;
            }

            @Override
            public boolean withdraw(int amount) {

                if (balance >= amount && notBlocked) {
                    balance -= amount;
                    return true;
                }
                if (blockingPolicy.test(amount, balance)) {// quando cerchi di prelevare di più di quello che hai
                    notBlocked = false;
                }
                return false;
            }

        };

    }

    @Override
    public BankAccount createWithFeeAndCredit(UnaryOperator<Integer> feeFunction, Predicate<Integer> allowedCredit,
            UnaryOperator<Integer> rateFunction) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'createWithFeeAndCredit'");
    }

}
