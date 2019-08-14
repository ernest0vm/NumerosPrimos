using System;
using System.Collections.Generic;
using System.Linq;
using System.Threading.Tasks;
using Microsoft.AspNetCore.Http;
using Microsoft.AspNetCore.Mvc;
using Microsoft.EntityFrameworkCore;
using API.Data;
using API.Models;

namespace API.Controllers
{
    [Route("api/[controller]")]
    [ApiController]
    public class NumbersController : ControllerBase
    {
        private readonly Context _context;

        public NumbersController(Context context)
        {
            _context = context;
        }

        // GET: /api/numbers?inicio=number&final=number
        [HttpGet]
        public IEnumerable<Numero> ObtenerNumerosPrimos(int inicio, int final)
        {
            List<Numero> list = new List<Numero>();
            Numero res;

            for (var a = inicio; a <= final; a++)
            {
                res = Helpers.HelperNumeros.EsNumeroPrimo(a);

                if (res.esPrimo == true)
                {
                    list.Add(res);
                }

            }

            //agrega nuevo registro de rango de numeros primos a base de datos
            Rango rango = new Rango
            {
                RangoInicial = inicio,
                RangoFinal = final,
                NumerosEncontrados = list.Count()
            };

            _context.Rangos.Add(rango);
            _context.SaveChanges();

            return list;
        }

        // GET: api/Numbers/number
        [HttpGet("{number}")]
        public IEnumerable<Numero> ObtenerNumeroPrimo(int number)
        {
            var res = Helpers.HelperNumeros.EsNumeroPrimo(number);
            var list = new List<Numero>();

            list.Add(res);
            return list;
        }

    }
}
