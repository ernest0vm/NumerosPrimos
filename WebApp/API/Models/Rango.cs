using System;
using System.Collections.Generic;
using System.Linq;
using System.Threading.Tasks;

namespace API.Models
{
    public class Rango
    {
        public int RangoId { get; set; }
        public int RangoInicial { get; set; }
        public int RangoFinal { get; set; }
        public int NumerosEncontrados { get; set; }
    }
}
