// Roman Class interface

const short MIN_VAL = 1;
const short MAX_VAL = 3999;
const short RN_NUM = 13;
const short RN_VAL[RN_NUM] = {1000,900,500,400,100,90,50,40,10,9,5,4,1};
const std::string RN_STR[RN_NUM] = {"M","CM","D","CD","C","XC","L","XL","X","IX","V","IV","I"};

class Roman
{
    public:
        Roman(short = 0);
        bool TakeDecimal(short);
        bool TakeNumeral(std::string); 
        Roman Add(Roman);
        Roman Sub(Roman);
        short GetDecimal() const;
        std::string GetNumeral() const;

    private:
        short decimal;
        std::string numeral;
};
