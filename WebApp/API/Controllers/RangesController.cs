using System;
using System.Collections.Generic;
using System.Linq;
using System.Threading.Tasks;
using API.Data;
using API.Models;
using Microsoft.AspNetCore.Http;
using Microsoft.AspNetCore.Mvc;

namespace API.Controllers
{
    [Route("api/[controller]")]
    [ApiController]
    public class RangesController : ControllerBase
    {
        private readonly Context _context;

        public RangesController(Context context)
        {
            _context = context;
        }

        [HttpGet]
        public IEnumerable<Rango> GetRanges()
        {
            return _context.Rangos.ToList();
        }
    }
}