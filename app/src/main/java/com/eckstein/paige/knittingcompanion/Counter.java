package com.eckstein.paige.knittingcompanion;

public class Counter {

    private int ones, tens, hundreds;
    String name;

    Counter()
    {
        ones = 0;
        tens = 0;
        hundreds = 0;
    }

    Counter(String name)
    {
        this.name = name;
        ones = 0;
        tens = 0;
        hundreds = 0;
    }

    public int getOnes()
    {
        return ones;
    }

    public int getTens() {
        return tens;
    }

    public int getHundreds() {
        return hundreds;
    }

    public void increment()
    {
        if(ones < 9)
        {
            ones++;
        }
        else
        {
            ones = 0;
            if(tens < 9)
            {
                tens++;
            }
            else
            {
                tens = 0;
                if(hundreds < 9)
                {
                    hundreds++;
                }
                else
                {
                    hundreds = 0;
                }
            }
        }

    }

    public void decrement()
    {
        if(ones >= 0)
        {
            ones--;
        }
        else
        {
            ones = 9;
            if(tens >= 0)
            {
                tens--;
            }
            else
            {
                tens = 9;
                if(hundreds >= 0)
                {
                    hundreds--;
                }
                else
                {
                    ones = 0;
                    tens = 0;
                    hundreds = 0;
                }
            }
        }
    }

    public void reset()
    {
        ones = 0;
        tens = 0;
        hundreds = 0;
    }
}
