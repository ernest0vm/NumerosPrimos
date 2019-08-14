﻿using API.Models;
using Microsoft.EntityFrameworkCore;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Threading.Tasks;

namespace API.Data
{
    public class Context : DbContext
    {
        public Context(DbContextOptions<Context> options) : base (options)
        {

        }

        public DbSet<Rango> Rangos { get; set; }
    }


}
