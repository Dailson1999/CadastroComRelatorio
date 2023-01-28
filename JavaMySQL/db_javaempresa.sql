-- phpMyAdmin SQL Dump
-- version 5.3.0-dev+20220530.a2456aa9a3
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Tempo de geração: 07-Dez-2022 às 01:54
-- Versão do servidor: 10.4.24-MariaDB
-- versão do PHP: 8.1.5

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Banco de dados: `db_javaempresa`
--

DELIMITER $$
--
-- Procedimentos
--
CREATE DEFINER=`root`@`localhost` PROCEDURE `Buscar_CPF` (IN `ID` INT, OUT `pCPF` VARCHAR(45))   begin 

    select cpf
    into pCPF
    from funcionarios
    where id = ID;

end$$

CREATE DEFINER=`root`@`localhost` PROCEDURE `Buscar_Endereco` (IN `ID` INT, OUT `pEndereco` VARCHAR(45))   begin 

    select endereco
    into pEndereco
    from funcionarios
    where id = ID;
end$$

CREATE DEFINER=`root`@`localhost` PROCEDURE `Classificacao_Cargo` (IN `ID` INT, OUT `pClassificacao` VARCHAR(45))   begin 

    declare pSalario float;

    select salario
    into pSalario
    from funcionarios
    where id = ID;

    if pSalario > 8000 then
        set pClassificacao = "Diretor";
    end if;

end$$

--
-- Funções
--
CREATE DEFINER=`root`@`localhost` FUNCTION `Busca_Nome_ID` (`ID` INT) RETURNS VARCHAR(45) CHARSET utf8mb4 DETERMINISTIC begin

    declare fNome varchar(45);

    select nome
    into fNome
    from funcionarios
    where id = ID;

    return (fNome);
end$$

CREATE DEFINER=`root`@`localhost` FUNCTION `Cargo_Gerente` (`ID` INT) RETURNS VARCHAR(45) CHARSET utf8mb4 DETERMINISTIC begin

    declare Gerente varchar(45);
    declare nome_funcionario varchar(45);

    select cargo
    into Gerente
    from funcionarios
    where id = ID;

    if Gerente = 'Gerente' or 'gerente' then
        select nome into nome_funcionario from funcionarios where id = ID;
    end if;

    return (nome_funcionario);
end$$

CREATE DEFINER=`root`@`localhost` FUNCTION `salario_funcionarios` (`ID` INT) RETURNS VARCHAR(10) CHARSET utf8mb4 DETERMINISTIC begin

    declare classe varchar(10);
    declare SalarioComparar float;

    select salario
    into SalarioComparar
    from funcionarios
    where id = ID;

    if SalarioComparar <1500 then
        set classe = "Classe C";
    elseif SalarioComparar >=1500 then
        set classe = "Classe B";
    elseif SalarioComparar >=2500 then
        set classe = "Classe A";
    end if;
    return (classe);
    
end$$

DELIMITER ;

-- --------------------------------------------------------

--
-- Estrutura da tabela `busca_cpf`
--

CREATE TABLE `busca_cpf` (
  `id` mediumint(9) NOT NULL,
  `nome` varchar(45) DEFAULT NULL,
  `cpf_do_id` varchar(45) DEFAULT NULL,
  `endereco` varchar(45) DEFAULT NULL,
  `cidade` varchar(45) DEFAULT NULL,
  `celular` varchar(45) DEFAULT NULL,
  `cargo` varchar(45) DEFAULT NULL,
  `salario` float DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Extraindo dados da tabela `busca_cpf`
--

INSERT INTO `busca_cpf` (`id`, `nome`, `cpf_do_id`, `endereco`, `cidade`, `celular`, `cargo`, `salario`) VALUES
(1, NULL, '1234', NULL, NULL, NULL, NULL, NULL),
(2, NULL, NULL, NULL, NULL, NULL, NULL, NULL);

-- --------------------------------------------------------

--
-- Estrutura da tabela `busca_diretor`
--

CREATE TABLE `busca_diretor` (
  `id` mediumint(9) NOT NULL,
  `nome` varchar(45) DEFAULT NULL,
  `cpf` varchar(45) DEFAULT NULL,
  `endereco` varchar(45) DEFAULT NULL,
  `cidade` varchar(45) DEFAULT NULL,
  `celular` varchar(45) DEFAULT NULL,
  `diretor_do_id` varchar(45) DEFAULT NULL,
  `salario` float DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Extraindo dados da tabela `busca_diretor`
--

INSERT INTO `busca_diretor` (`id`, `nome`, `cpf`, `endereco`, `cidade`, `celular`, `diretor_do_id`, `salario`) VALUES
(1, NULL, NULL, NULL, NULL, NULL, 'Diretor', NULL);

-- --------------------------------------------------------

--
-- Estrutura da tabela `busca_endereco`
--

CREATE TABLE `busca_endereco` (
  `id` mediumint(9) NOT NULL,
  `nome` varchar(45) DEFAULT NULL,
  `cpf` varchar(45) DEFAULT NULL,
  `endereco_do_id` varchar(45) DEFAULT NULL,
  `cidade` varchar(45) DEFAULT NULL,
  `celular` varchar(45) DEFAULT NULL,
  `cargo` varchar(45) DEFAULT NULL,
  `salario` float DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Extraindo dados da tabela `busca_endereco`
--

INSERT INTO `busca_endereco` (`id`, `nome`, `cpf`, `endereco_do_id`, `cidade`, `celular`, `cargo`, `salario`) VALUES
(1, NULL, NULL, 'rua 1', NULL, NULL, NULL, NULL);

-- --------------------------------------------------------

--
-- Estrutura da tabela `busca_nome`
--

CREATE TABLE `busca_nome` (
  `id` mediumint(9) NOT NULL,
  `Resultado_Busca` varchar(45) DEFAULT NULL,
  `cpf` varchar(45) DEFAULT NULL,
  `endereco` varchar(45) DEFAULT NULL,
  `cidade` varchar(45) DEFAULT NULL,
  `celular` varchar(45) DEFAULT NULL,
  `cargo` varchar(45) DEFAULT NULL,
  `salario` float DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Extraindo dados da tabela `busca_nome`
--

INSERT INTO `busca_nome` (`id`, `Resultado_Busca`, `cpf`, `endereco`, `cidade`, `celular`, `cargo`, `salario`) VALUES
(1, 'dailson', NULL, NULL, NULL, NULL, NULL, NULL);

-- --------------------------------------------------------

--
-- Estrutura da tabela `cargo_gerente`
--

CREATE TABLE `cargo_gerente` (
  `id` mediumint(9) NOT NULL,
  `gerente_nome` varchar(45) DEFAULT NULL,
  `cpf` varchar(45) DEFAULT NULL,
  `endereco` varchar(45) DEFAULT NULL,
  `cidade` varchar(45) DEFAULT NULL,
  `celular` varchar(45) DEFAULT NULL,
  `cargo` varchar(45) DEFAULT NULL,
  `salario` float DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Extraindo dados da tabela `cargo_gerente`
--

INSERT INTO `cargo_gerente` (`id`, `gerente_nome`, `cpf`, `endereco`, `cidade`, `celular`, `cargo`, `salario`) VALUES
(1, 'dailson', NULL, NULL, NULL, NULL, NULL, NULL);

-- --------------------------------------------------------

--
-- Estrutura da tabela `classe`
--

CREATE TABLE `classe` (
  `NivelClasse` varchar(45) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- --------------------------------------------------------

--
-- Estrutura da tabela `funcionarios`
--

CREATE TABLE `funcionarios` (
  `id` mediumint(9) NOT NULL,
  `nome` varchar(45) DEFAULT NULL,
  `cpf` varchar(45) DEFAULT NULL,
  `endereco` varchar(45) DEFAULT NULL,
  `cidade` varchar(45) DEFAULT NULL,
  `celular` varchar(45) DEFAULT NULL,
  `cargo` varchar(45) DEFAULT NULL,
  `salario` float DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Extraindo dados da tabela `funcionarios`
--

INSERT INTO `funcionarios` (`id`, `nome`, `cpf`, `endereco`, `cidade`, `celular`, `cargo`, `salario`) VALUES
(1, 'Dailson', '97547525896', 'rua Mem de Sá', 'Hortolândia', '19988417698', 'Diretor', 8000),
(3, 'Líliam Carneiro Zanata', '82269262891', 'Rua Major juca Neto, n 355 Jardim Santo', 'Campinas', '36232898', 'Presidente', 8000),
(4, 'Nidelse Bassi', '13784518800', ' Av. Dona Gertrudes, nº 86 – Centro', 'Campinas', '36223896', 'Vice Presidente', 4000),
(5, 'Marlene Marino', '054659.1868', 'Rua: Belo Horizonte, 72 – Centro', 'Hortolândia', '36231832', 'Tesoureira', 2500),
(6, ' Luiza Helena', '15455533860', ' Rua João Ciacco, nº139 - Jardim Leonor', 'Hortolândia', '36231159', 'Tesoureira', 2500),
(7, 'Maria do Carmo Zan', '72379618887', 'Rua José Marcondes, 50 - Jardim Priscila', 'Monte Mor', '36311550', 'Secretaria', 2000),
(8, 'Haline Adriana Roza Marani', '34988012875', 'Rua Azulão, 150 – Recanto dos Pássaros', 'Montemor', '36235152', 'Secretaria', 1500);

--
-- Acionadores `funcionarios`
--
DELIMITER $$
CREATE TRIGGER `before_funcionarios_deletados` BEFORE DELETE ON `funcionarios` FOR EACH ROW begin
    
    insert into funcionarios_deletados (action, changedat,nome2, cpf2 ,cargo,salario)
    values('delete', now(), old.nome,old.cpf,old.cargo,old.salario);
    
    end
$$
DELIMITER ;
DELIMITER $$
CREATE TRIGGER `before_funcionarios_update_endereco` BEFORE UPDATE ON `funcionarios` FOR EACH ROW begin
    
    insert into funcionarios_endereco_antes (action, changedat,nome, cpf ,endereco2,cidade2,celular2)
    values('update', now(), old.nome,old.cpf,old.endereco,old.cidade,old.celular);
    
    end
$$
DELIMITER ;
DELIMITER $$
CREATE TRIGGER `before_funcionarios_update_salario` BEFORE UPDATE ON `funcionarios` FOR EACH ROW begin
    
    insert into funcionarios_cargo_salario_antes (action, changedat,nome, cpf ,cargo2,salario2)
    values('update', now(), old.nome,old.cpf,old.cargo,old.salario);
    
    end
$$
DELIMITER ;

-- --------------------------------------------------------

--
-- Estrutura da tabela `funcionarios_cargo_salario_antes`
--

CREATE TABLE `funcionarios_cargo_salario_antes` (
  `id` int(11) NOT NULL,
  `nome` varchar(45) DEFAULT NULL,
  `cpf` varchar(45) NOT NULL,
  `cargo2` varchar(45) DEFAULT NULL,
  `salario2` float DEFAULT NULL,
  `changedat` datetime DEFAULT NULL,
  `action` varchar(50) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Extraindo dados da tabela `funcionarios_cargo_salario_antes`
--

INSERT INTO `funcionarios_cargo_salario_antes` (`id`, `nome`, `cpf`, `cargo2`, `salario2`, `changedat`, `action`) VALUES
(1, 'dailson', '1234', 'Gerente', 30000, '2022-12-06 11:16:59', 'update'),
(2, 'dailson', '1234', 'empresario', 22000, '2022-12-06 17:57:49', 'update'),
(3, 'Dailson', '97547525896', 'Gerente', 5000, '2022-12-06 18:11:28', 'update'),
(4, 'Dailson', '97547525896', 'Diretor', 5000, '2022-12-06 18:11:36', 'update'),
(5, 'Adir Pereira Silva', '34763090', 'Fiscal', 1300, '2022-12-06 21:40:43', 'update');

-- --------------------------------------------------------

--
-- Estrutura da tabela `funcionarios_deletados`
--

CREATE TABLE `funcionarios_deletados` (
  `id` int(11) NOT NULL,
  `nome2` varchar(45) DEFAULT NULL,
  `cpf2` varchar(45) NOT NULL,
  `cargo` varchar(45) DEFAULT NULL,
  `salario` float DEFAULT NULL,
  `changedat` datetime DEFAULT NULL,
  `action` varchar(50) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Extraindo dados da tabela `funcionarios_deletados`
--

INSERT INTO `funcionarios_deletados` (`id`, `nome2`, `cpf2`, `cargo`, `salario`, `changedat`, `action`) VALUES
(1, 'Thomas', '147852', 'empresario', 3000, '2022-12-06 11:29:36', 'delete'),
(2, 'Adir Pereira Silva', '34763090', 'Fiscal', 1300, '2022-12-06 21:40:59', 'delete');

-- --------------------------------------------------------

--
-- Estrutura da tabela `funcionarios_endereco_antes`
--

CREATE TABLE `funcionarios_endereco_antes` (
  `id` int(11) NOT NULL,
  `nome` varchar(45) DEFAULT NULL,
  `cpf` varchar(45) NOT NULL,
  `endereco2` varchar(45) DEFAULT NULL,
  `cidade2` varchar(45) DEFAULT NULL,
  `celular2` int(11) DEFAULT NULL,
  `changedat` datetime DEFAULT NULL,
  `action` varchar(50) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Extraindo dados da tabela `funcionarios_endereco_antes`
--

INSERT INTO `funcionarios_endereco_antes` (`id`, `nome`, `cpf`, `endereco2`, `cidade2`, `celular2`, `changedat`, `action`) VALUES
(1, 'dailson', '1234', 'rua 41', 'Sao paulo', 19, '2022-12-06 11:16:59', 'update'),
(2, 'dailson', '1234', 'rua 41', 'Sao paulo', 19, '2022-12-06 17:57:49', 'update'),
(3, 'Dailson', '97547525896', 'rua Mem de Sá', 'Hortolândia', 2147483647, '2022-12-06 18:11:28', 'update'),
(4, 'Dailson', '97547525896', 'rua Mem de Sá', 'Hortolândia', 2147483647, '2022-12-06 18:11:36', 'update'),
(5, 'Adir Pereira Silva', '34763090', 'Rua: Santiago Penha, nº128- Rosário', 'Mogi Mirin', 36231609, '2022-12-06 21:40:43', 'update');

-- --------------------------------------------------------

--
-- Estrutura da tabela `salario_funcionarios`
--

CREATE TABLE `salario_funcionarios` (
  `id` mediumint(9) NOT NULL,
  `classe` varchar(45) DEFAULT NULL,
  `cpf` varchar(45) DEFAULT NULL,
  `endereco` varchar(45) DEFAULT NULL,
  `cidade` varchar(45) DEFAULT NULL,
  `celular` varchar(45) DEFAULT NULL,
  `cargo` varchar(45) DEFAULT NULL,
  `salario` float DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Extraindo dados da tabela `salario_funcionarios`
--

INSERT INTO `salario_funcionarios` (`id`, `classe`, `cpf`, `endereco`, `cidade`, `celular`, `cargo`, `salario`) VALUES
(1, 'Classe B', NULL, NULL, NULL, NULL, NULL, NULL);

-- --------------------------------------------------------

--
-- Estrutura stand-in para vista `vw_deletados`
-- (Veja abaixo para a view atual)
--
CREATE TABLE `vw_deletados` (
`id` mediumint(9)
,`nome` varchar(45)
,`cpf` varchar(45)
,`nome2` varchar(45)
,`cpf2` varchar(45)
);

-- --------------------------------------------------------

--
-- Estrutura stand-in para vista `vw_endereco`
-- (Veja abaixo para a view atual)
--
CREATE TABLE `vw_endereco` (
`id` mediumint(9)
,`nome` varchar(45)
,`endereco` varchar(45)
,`cidade` varchar(45)
,`celular` varchar(45)
,`endereco2` varchar(45)
,`cidade2` varchar(45)
,`celular2` int(11)
);

-- --------------------------------------------------------

--
-- Estrutura stand-in para vista `vw_salario`
-- (Veja abaixo para a view atual)
--
CREATE TABLE `vw_salario` (
`id` mediumint(9)
,`nome` varchar(45)
,`cargo` varchar(45)
,`salario` float
,`cargo2` varchar(45)
,`salario2` float
);

-- --------------------------------------------------------

--
-- Estrutura stand-in para vista `vw_ultimaattendereco`
-- (Veja abaixo para a view atual)
--
CREATE TABLE `vw_ultimaattendereco` (
`id` mediumint(9)
,`nome` varchar(45)
,`cpf` varchar(45)
,`changedat` datetime
);

-- --------------------------------------------------------

--
-- Estrutura stand-in para vista `vw_ultimaattsalarial`
-- (Veja abaixo para a view atual)
--
CREATE TABLE `vw_ultimaattsalarial` (
`id` mediumint(9)
,`nome` varchar(45)
,`cpf` varchar(45)
,`changedat` datetime
);

-- --------------------------------------------------------

--
-- Estrutura para vista `vw_deletados`
--
DROP TABLE IF EXISTS `vw_deletados`;

CREATE ALGORITHM=UNDEFINED DEFINER=`root`@`localhost` SQL SECURITY DEFINER VIEW `vw_deletados`  AS SELECT `funcionarios`.`id` AS `id`, `funcionarios`.`nome` AS `nome`, `funcionarios`.`cpf` AS `cpf`, `funcionarios_deletados`.`nome2` AS `nome2`, `funcionarios_deletados`.`cpf2` AS `cpf2` FROM (`funcionarios` join `funcionarios_deletados` on(`funcionarios`.`cpf` <> `funcionarios_deletados`.`cpf2`))  ;

-- --------------------------------------------------------

--
-- Estrutura para vista `vw_endereco`
--
DROP TABLE IF EXISTS `vw_endereco`;

CREATE ALGORITHM=UNDEFINED DEFINER=`root`@`localhost` SQL SECURITY DEFINER VIEW `vw_endereco`  AS SELECT `funcionarios`.`id` AS `id`, `funcionarios`.`nome` AS `nome`, `funcionarios`.`endereco` AS `endereco`, `funcionarios`.`cidade` AS `cidade`, `funcionarios`.`celular` AS `celular`, `funcionarios_endereco_antes`.`endereco2` AS `endereco2`, `funcionarios_endereco_antes`.`cidade2` AS `cidade2`, `funcionarios_endereco_antes`.`celular2` AS `celular2` FROM (`funcionarios` join `funcionarios_endereco_antes` on(`funcionarios`.`cpf` = `funcionarios_endereco_antes`.`cpf`))  ;

-- --------------------------------------------------------

--
-- Estrutura para vista `vw_salario`
--
DROP TABLE IF EXISTS `vw_salario`;

CREATE ALGORITHM=UNDEFINED DEFINER=`root`@`localhost` SQL SECURITY DEFINER VIEW `vw_salario`  AS SELECT `funcionarios`.`id` AS `id`, `funcionarios`.`nome` AS `nome`, `funcionarios`.`cargo` AS `cargo`, `funcionarios`.`salario` AS `salario`, `funcionarios_cargo_salario_antes`.`cargo2` AS `cargo2`, `funcionarios_cargo_salario_antes`.`salario2` AS `salario2` FROM (`funcionarios` join `funcionarios_cargo_salario_antes` on(`funcionarios`.`cpf` = `funcionarios_cargo_salario_antes`.`cpf`))  ;

-- --------------------------------------------------------

--
-- Estrutura para vista `vw_ultimaattendereco`
--
DROP TABLE IF EXISTS `vw_ultimaattendereco`;

CREATE ALGORITHM=UNDEFINED DEFINER=`root`@`localhost` SQL SECURITY DEFINER VIEW `vw_ultimaattendereco`  AS SELECT `funcionarios`.`id` AS `id`, `funcionarios`.`nome` AS `nome`, `funcionarios`.`cpf` AS `cpf`, `funcionarios_endereco_antes`.`changedat` AS `changedat` FROM (`funcionarios` join `funcionarios_endereco_antes` on(`funcionarios`.`cpf` = `funcionarios_endereco_antes`.`cpf`))  ;

-- --------------------------------------------------------

--
-- Estrutura para vista `vw_ultimaattsalarial`
--
DROP TABLE IF EXISTS `vw_ultimaattsalarial`;

CREATE ALGORITHM=UNDEFINED DEFINER=`root`@`localhost` SQL SECURITY DEFINER VIEW `vw_ultimaattsalarial`  AS SELECT `funcionarios`.`id` AS `id`, `funcionarios`.`nome` AS `nome`, `funcionarios`.`cpf` AS `cpf`, `funcionarios_cargo_salario_antes`.`changedat` AS `changedat` FROM (`funcionarios` join `funcionarios_cargo_salario_antes` on(`funcionarios`.`cpf` = `funcionarios_cargo_salario_antes`.`cpf`))  ;

--
-- Índices para tabelas despejadas
--

--
-- Índices para tabela `busca_cpf`
--
ALTER TABLE `busca_cpf`
  ADD PRIMARY KEY (`id`);

--
-- Índices para tabela `busca_diretor`
--
ALTER TABLE `busca_diretor`
  ADD PRIMARY KEY (`id`);

--
-- Índices para tabela `busca_endereco`
--
ALTER TABLE `busca_endereco`
  ADD PRIMARY KEY (`id`);

--
-- Índices para tabela `busca_nome`
--
ALTER TABLE `busca_nome`
  ADD PRIMARY KEY (`id`);

--
-- Índices para tabela `cargo_gerente`
--
ALTER TABLE `cargo_gerente`
  ADD PRIMARY KEY (`id`);

--
-- Índices para tabela `funcionarios`
--
ALTER TABLE `funcionarios`
  ADD PRIMARY KEY (`id`);

--
-- Índices para tabela `funcionarios_cargo_salario_antes`
--
ALTER TABLE `funcionarios_cargo_salario_antes`
  ADD PRIMARY KEY (`id`);

--
-- Índices para tabela `funcionarios_deletados`
--
ALTER TABLE `funcionarios_deletados`
  ADD PRIMARY KEY (`id`);

--
-- Índices para tabela `funcionarios_endereco_antes`
--
ALTER TABLE `funcionarios_endereco_antes`
  ADD PRIMARY KEY (`id`);

--
-- Índices para tabela `salario_funcionarios`
--
ALTER TABLE `salario_funcionarios`
  ADD PRIMARY KEY (`id`);

--
-- AUTO_INCREMENT de tabelas despejadas
--

--
-- AUTO_INCREMENT de tabela `busca_cpf`
--
ALTER TABLE `busca_cpf`
  MODIFY `id` mediumint(9) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=3;

--
-- AUTO_INCREMENT de tabela `busca_diretor`
--
ALTER TABLE `busca_diretor`
  MODIFY `id` mediumint(9) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=2;

--
-- AUTO_INCREMENT de tabela `busca_endereco`
--
ALTER TABLE `busca_endereco`
  MODIFY `id` mediumint(9) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=2;

--
-- AUTO_INCREMENT de tabela `busca_nome`
--
ALTER TABLE `busca_nome`
  MODIFY `id` mediumint(9) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=2;

--
-- AUTO_INCREMENT de tabela `cargo_gerente`
--
ALTER TABLE `cargo_gerente`
  MODIFY `id` mediumint(9) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=2;

--
-- AUTO_INCREMENT de tabela `funcionarios`
--
ALTER TABLE `funcionarios`
  MODIFY `id` mediumint(9) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=10;

--
-- AUTO_INCREMENT de tabela `funcionarios_cargo_salario_antes`
--
ALTER TABLE `funcionarios_cargo_salario_antes`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=6;

--
-- AUTO_INCREMENT de tabela `funcionarios_deletados`
--
ALTER TABLE `funcionarios_deletados`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=3;

--
-- AUTO_INCREMENT de tabela `funcionarios_endereco_antes`
--
ALTER TABLE `funcionarios_endereco_antes`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=6;

--
-- AUTO_INCREMENT de tabela `salario_funcionarios`
--
ALTER TABLE `salario_funcionarios`
  MODIFY `id` mediumint(9) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=2;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;



