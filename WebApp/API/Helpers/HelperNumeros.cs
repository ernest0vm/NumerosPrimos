using API.Models;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Threading.Tasks;

namespace API.Helpers
{
    public class HelperNumeros
    {
        public static Numero EsNumeroPrimo(int numero)
        {

            Numero NumeroResponse = new Numero
            {
                numero = numero,
                esPrimo = true
            };

            if (numero == 1)
                NumeroResponse.esPrimo = false; //1 no es considerado numero primo.

            int a = 0, i;
            for (i = 1; i < (numero + 1); i++)
            {
                if (numero % i == 0)
                {
                    a++;
                }
            }
            if (a != 2)
            {
                NumeroResponse.esPrimo = false;
            }
            else
            {
                NumeroResponse.esPrimo = true;
            }

            return NumeroResponse;
        }
    }
}
