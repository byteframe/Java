#include <string>
#include "Roman.h"

Roman::Roman(short conD)
{
    if (conD >= MIN_VAL && conD <= MAX_VAL)
        TakeDecimal(conD);
    else
    {
        decimal = 0;
        numeral = "Empty";
    }
}

bool Roman::TakeDecimal(short toTake)
{
    if (toTake >= MIN_VAL && toTake <= MAX_VAL)
    {
        decimal = toTake;
        numeral = "";
        for (short amAt = 0; amAt < RN_NUM; amAt++)
            for (short many = toTake / RN_VAL[amAt]; many > 0; many--)
            {
                numeral += RN_STR[amAt];
                toTake -= RN_VAL[amAt];
            }
        return true;
    }
    else
        return false;
}

bool Roman::TakeNumeral(std::string toTake)
{
    decimal = 0;
    std::transform(toTake.begin(),toTake.end(), toTake.begin(), toupper);
    numeral = toTake;
    short upTo;
    for (short cnt = 0; cnt < toTake.length(); cnt++)
    {
        if (toTake.substr(cnt, 2) == RN_STR[1] || toTake.substr(cnt, 2) == RN_STR[3] || toTake.substr(cnt, 2) == RN_STR[9] ||
           toTake.substr(cnt, 2) == RN_STR[5] || toTake.substr(cnt, 2) == RN_STR[7] || toTake.substr(cnt, 2) == RN_STR[11])
            upTo = 2;
        else
            upTo = 1;
        for (short whr = 0; whr < RN_NUM; whr++)
            if (toTake.substr(cnt, upTo) == RN_STR[whr])   
                decimal += RN_VAL[whr];
        if (upTo == 2)
            cnt++;
    }
    Roman toCompare(decimal);
    if (numeral.compare(toCompare.GetNumeral()) == 0)
        return true;
    else
        return false;
}

Roman Roman::Add(Roman toAdd)
{
    TakeDecimal(decimal + toAdd.decimal);
}

Roman Roman::Sub(Roman toSub)
{
    TakeDecimal(decimal - toSub.decimal);
}

short Roman::GetDecimal() const
{
    return decimal;
}

std::string Roman::GetNumeral() const
{
    return numeral;
}
