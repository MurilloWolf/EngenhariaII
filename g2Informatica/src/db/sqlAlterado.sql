-- MySQL Workbench Forward Engineering

SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='ONLY_FULL_GROUP_BY,STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_ENGINE_SUBSTITUTION';

-- -----------------------------------------------------
-- Schema mydb
-- -----------------------------------------------------
-- -----------------------------------------------------
-- Schema bancoG2
-- -----------------------------------------------------

-- -----------------------------------------------------
-- Schema bancoG2
-- -----------------------------------------------------
CREATE SCHEMA IF NOT EXISTS `bancoG2` DEFAULT CHARACTER SET latin1 ;
USE `bancoG2` ;

-- -----------------------------------------------------
-- Table `bancoG2`.`Endereco`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `bancoG2`.`Endereco` (
  `end_cod` INT(11) NOT NULL AUTO_INCREMENT,
  `end_uf` VARCHAR(3) NOT NULL,
  `end_cidade` VARCHAR(65) NULL DEFAULT NULL,
  `end_bairro` VARCHAR(65) NULL DEFAULT NULL,
  `end_numero` VARCHAR(45) NULL DEFAULT NULL,
  `end_apto` VARCHAR(45) NULL DEFAULT NULL,
  `end_cep` VARCHAR(45) NULL DEFAULT NULL,
  `end_rua` VARCHAR(100) NULL DEFAULT NULL,
  PRIMARY KEY (`end_cod`))
ENGINE = InnoDB
AUTO_INCREMENT = 24
DEFAULT CHARACTER SET = latin1;


-- -----------------------------------------------------
-- Table `bancoG2`.`Cliente`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `bancoG2`.`Cliente` (
  `cli_cod` INT(11) NOT NULL AUTO_INCREMENT,
  `cli_nome` VARCHAR(65) NOT NULL,
  `cli_email` VARCHAR(45) NOT NULL,
  `cli_telefone` VARCHAR(45) NOT NULL,
  `cli_doc` VARCHAR(45) NULL DEFAULT NULL,
  `cli_cpf` VARCHAR(45) NULL DEFAULT NULL,
  `cli_rg` VARCHAR(45) NULL DEFAULT NULL,
  `cli_dataNascimento` DATE NULL DEFAULT NULL,
  `Endereco_end_cod` INT(11) NOT NULL,
  PRIMARY KEY (`cli_cod`),
  INDEX `fk_Cliente_Endereco1_idx` (`Endereco_end_cod` ASC) VISIBLE,
  CONSTRAINT `fk_Cliente_Endereco1`
    FOREIGN KEY (`Endereco_end_cod`)
    REFERENCES `bancoG2`.`Endereco` (`end_cod`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB
DEFAULT CHARACTER SET = latin1;


-- -----------------------------------------------------
-- Table `bancoG2`.`Fornecedor`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `bancoG2`.`Fornecedor` (
  `for_cod` INT(11) NOT NULL AUTO_INCREMENT,
  `for_nome` VARCHAR(65) NOT NULL,
  `for_email` VARCHAR(65) NOT NULL,
  `for_telefone` VARCHAR(45) NOT NULL,
  `for_doc` VARCHAR(45) NULL DEFAULT NULL,
  `for_contato` VARCHAR(45) NULL DEFAULT NULL,
  `Endereco_end_cod` INT(11) NOT NULL,
  PRIMARY KEY (`for_cod`),
  INDEX `fk_Fornecedor_Endereco1_idx` (`Endereco_end_cod` ASC) VISIBLE,
  CONSTRAINT `fk_Fornecedor_Endereco1`
    FOREIGN KEY (`Endereco_end_cod`)
    REFERENCES `bancoG2`.`Endereco` (`end_cod`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB
AUTO_INCREMENT = 4
DEFAULT CHARACTER SET = latin1;


-- -----------------------------------------------------
-- Table `bancoG2`.`Compra`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `bancoG2`.`Compra` (
  `com_cod` INT(11) NOT NULL AUTO_INCREMENT,
  `com_data` TIMESTAMP(6) NOT NULL DEFAULT CURRENT_TIMESTAMP(6) ON UPDATE CURRENT_TIMESTAMP(6),
  `com_valorTotal` DECIMAL(5,2) NOT NULL,
  `com_numeroNf` VARCHAR(45) NULL DEFAULT NULL,
  `com_for_cod` INT(11) NOT NULL,
  PRIMARY KEY (`com_cod`),
  INDEX `fk_Compra_Fornecedor1_idx` (`com_for_cod` ASC) VISIBLE,
  CONSTRAINT `fk_Compra_Fornecedor1`
    FOREIGN KEY (`com_for_cod`)
    REFERENCES `bancoG2`.`Fornecedor` (`for_cod`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB
DEFAULT CHARACTER SET = latin1;


-- -----------------------------------------------------
-- Table `bancoG2`.`Produto`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `bancoG2`.`Produto` (
  `pro_cod` INT(11) NOT NULL AUTO_INCREMENT,
  `pro_nome` VARCHAR(45) NOT NULL,
  `pro_preco` DOUBLE NULL DEFAULT NULL,
  `pro_desc` VARCHAR(45) NULL DEFAULT NULL,
  `pro_quantidade` INT(11) NULL DEFAULT NULL,
  `pro_marca` VARCHAR(100) NULL DEFAULT NULL,
  PRIMARY KEY (`pro_cod`))
ENGINE = InnoDB
AUTO_INCREMENT = 10
DEFAULT CHARACTER SET = latin1;


-- -----------------------------------------------------
-- Table `bancoG2`.`Compra_Produto`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `bancoG2`.`Compra_Produto` (
  `Compra_com_cod` INT(11) NOT NULL,
  `Produto_pro_cod` INT(11) NOT NULL,
  `com_pro_quatidade` INT(11) NULL DEFAULT NULL,
  `com_pro_valor` DECIMAL(5,2) NULL DEFAULT NULL,
  PRIMARY KEY (`Compra_com_cod`, `Produto_pro_cod`),
  INDEX `fk_Compra_has_Produto_Produto1_idx` (`Produto_pro_cod` ASC) VISIBLE,
  INDEX `fk_Compra_has_Produto_Compra1_idx` (`Compra_com_cod` ASC) VISIBLE,
  CONSTRAINT `fk_Compra_has_Produto_Compra1`
    FOREIGN KEY (`Compra_com_cod`)
    REFERENCES `bancoG2`.`Compra` (`com_cod`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_Compra_has_Produto_Produto1`
    FOREIGN KEY (`Produto_pro_cod`)
    REFERENCES `bancoG2`.`Produto` (`pro_cod`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB
DEFAULT CHARACTER SET = latin1;


-- -----------------------------------------------------
-- Table `bancoG2`.`Contas_Pagar`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `bancoG2`.`Contas_Pagar` (
  `conp_cod` INT(11) NOT NULL AUTO_INCREMENT,
  `conp_valorPago` DECIMAL(5,2) NULL DEFAULT NULL,
  `conp_valorConta` DECIMAL(5,2) NULL DEFAULT NULL,
  `conp_dataConta` DATE NULL DEFAULT NULL,
  `conp_dataVencimento` DATE NULL DEFAULT NULL,
  `conp_dataPagamento` DATE NULL DEFAULT NULL,
  `Compra_com_cod` INT(11) NOT NULL,
  PRIMARY KEY (`conp_cod`),
  INDEX `fk_Contas_Pagar_Compra1_idx` (`Compra_com_cod` ASC) VISIBLE,
  CONSTRAINT `fk_Contas_Pagar_Compra1`
    FOREIGN KEY (`Compra_com_cod`)
    REFERENCES `bancoG2`.`Compra` (`com_cod`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB
DEFAULT CHARACTER SET = latin1;


-- -----------------------------------------------------
-- Table `bancoG2`.`Funcionario`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `bancoG2`.`Funcionario` (
  `fun_cod` INT(11) NOT NULL AUTO_INCREMENT,
  `fun_nome` VARCHAR(45) NOT NULL,
  `fun_email` VARCHAR(45) NOT NULL,
  `fun_telefone` VARCHAR(45) NOT NULL,
  `fun_cpf` VARCHAR(45) NOT NULL,
  `fun_rg` VARCHAR(45) NULL DEFAULT NULL,
  `Endereco_end_cod` INT(11) NOT NULL,
  `fun_id_login` VARCHAR(100) NULL DEFAULT NULL,
  `fun_senha` VARCHAR(100) NULL DEFAULT NULL,
  `fun_tipo` VARCHAR(100) NULL DEFAULT NULL,
  `fun_nivel` VARCHAR(100) NULL DEFAULT NULL,
  PRIMARY KEY (`fun_cod`),
  INDEX `fk_Funcionario_Endereco1_idx` (`Endereco_end_cod` ASC) VISIBLE,
  CONSTRAINT `fk_Funcionario_Endereco1`
    FOREIGN KEY (`Endereco_end_cod`)
    REFERENCES `bancoG2`.`Endereco` (`end_cod`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB
AUTO_INCREMENT = 10
DEFAULT CHARACTER SET = latin1;


-- -----------------------------------------------------
-- Table `bancoG2`.`Venda`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `bancoG2`.`Venda` (
  `ven_cod` INT(11) NOT NULL AUTO_INCREMENT,
  `ven_valorTotal` DECIMAL(5,2) NOT NULL,
  `ven_data` TIMESTAMP(6) NOT NULL DEFAULT CURRENT_TIMESTAMP(6) ON UPDATE CURRENT_TIMESTAMP(6),
  `ven_fun_cod` INT(11) NOT NULL,
  `ven_cli_cod` INT(11) NOT NULL,
  PRIMARY KEY (`ven_cod`),
  INDEX `fk_Venda_Funcionario1_idx` (`ven_fun_cod` ASC) VISIBLE,
  INDEX `fk_Venda_Cliente1_idx` (`ven_cli_cod` ASC) VISIBLE,
  CONSTRAINT `fk_Venda_Cliente1`
    FOREIGN KEY (`ven_cli_cod`)
    REFERENCES `bancoG2`.`Cliente` (`cli_cod`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_Venda_Funcionario1`
    FOREIGN KEY (`ven_fun_cod`)
    REFERENCES `bancoG2`.`Funcionario` (`fun_cod`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB
DEFAULT CHARACTER SET = latin1;


-- -----------------------------------------------------
-- Table `bancoG2`.`Contas_Receber`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `bancoG2`.`Contas_Receber` (
  `conr_cod` INT(11) NOT NULL AUTO_INCREMENT,
  `conr_valorReceber` DECIMAL(5,2) NULL DEFAULT NULL,
  `conr_valorRecebido` DECIMAL(5,2) NULL DEFAULT NULL,
  `conr_data` TIMESTAMP(6) NULL DEFAULT NULL,
  `conr_dataVencimento` DATE NULL DEFAULT NULL,
  `conr_dataPagamento` DATE NULL DEFAULT NULL,
  `Venda_ven_cod` INT(11) NOT NULL,
  PRIMARY KEY (`conr_cod`),
  INDEX `fk_Contas_Receber_Venda1_idx` (`Venda_ven_cod` ASC) VISIBLE,
  CONSTRAINT `fk_Contas_Receber_Venda1`
    FOREIGN KEY (`Venda_ven_cod`)
    REFERENCES `bancoG2`.`Venda` (`ven_cod`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB
DEFAULT CHARACTER SET = latin1;


-- -----------------------------------------------------
-- Table `bancoG2`.`Despesa`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `bancoG2`.`Despesa` (
  `des_cod` INT(11) NOT NULL AUTO_INCREMENT,
  `des_desc` VARCHAR(45) NULL DEFAULT NULL,
  `Contas_Pagar_conp_cod` INT(11) NOT NULL,
  PRIMARY KEY (`des_cod`),
  INDEX `fk_Despesa_Contas_Pagar1_idx` (`Contas_Pagar_conp_cod` ASC) VISIBLE,
  CONSTRAINT `fk_Despesa_Contas_Pagar1`
    FOREIGN KEY (`Contas_Pagar_conp_cod`)
    REFERENCES `bancoG2`.`Contas_Pagar` (`conp_cod`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB
DEFAULT CHARACTER SET = latin1;


-- -----------------------------------------------------
-- Table `bancoG2`.`Empresa`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `bancoG2`.`Empresa` (
  `emp_cod` INT(1) NOT NULL,
  `emp_nome` VARCHAR(45) NOT NULL,
  `emp_email` VARCHAR(45) NOT NULL,
  `emp_missao` VARCHAR(255) NULL DEFAULT NULL,
  `emp_img_pequena` VARCHAR(255) NULL DEFAULT NULL,
  `emp_img_grande` VARCHAR(255) NULL DEFAULT NULL,
  `emp_nomeFict` VARCHAR(45) NULL DEFAULT NULL,
  `Endereco_end_cod` INT(11) NULL DEFAULT NULL,
  `emp_cep` VARCHAR(100) NULL DEFAULT NULL,
  `emp_telefone` VARCHAR(100) NULL DEFAULT NULL,
  `emp_cnpj` VARCHAR(100) NULL DEFAULT NULL,
  `emp_paginaFb` VARCHAR(100) NULL DEFAULT NULL,
  `emp_instagram` VARCHAR(100) NULL DEFAULT NULL,
  `emp_site` VARCHAR(100) NULL DEFAULT NULL,
  PRIMARY KEY (`emp_cod`),
  INDEX `fk_Empresa_Endereco1_idx` (`Endereco_end_cod` ASC) VISIBLE,
  CONSTRAINT `fk_Empresa_Endereco1`
    FOREIGN KEY (`Endereco_end_cod`)
    REFERENCES `bancoG2`.`Endereco` (`end_cod`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB
DEFAULT CHARACTER SET = latin1;


-- -----------------------------------------------------
-- Table `bancoG2`.`Oferta`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `bancoG2`.`Oferta` (
  `ofe_cod` INT(11) NOT NULL AUTO_INCREMENT,
  `ofe_dataInicio` TIMESTAMP(6) NULL DEFAULT NULL,
  `ofe_dataFinal` TIMESTAMP(6) NULL DEFAULT NULL,
  `ofe_desc` VARCHAR(45) NULL DEFAULT NULL,
  `Funcionario_fun_cod` INT(11) NOT NULL,
  PRIMARY KEY (`ofe_cod`),
  INDEX `fk_Oferta_Funcionario1_idx` (`Funcionario_fun_cod` ASC) VISIBLE,
  CONSTRAINT `fk_Oferta_Funcionario1`
    FOREIGN KEY (`Funcionario_fun_cod`)
    REFERENCES `bancoG2`.`Funcionario` (`fun_cod`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB
AUTO_INCREMENT = 2
DEFAULT CHARACTER SET = latin1;


-- -----------------------------------------------------
-- Table `bancoG2`.`Servico`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `bancoG2`.`Servico` (
  `ser_cod` INT(11) NOT NULL AUTO_INCREMENT,
  `ser_desc` VARCHAR(45) NOT NULL,
  `ser_valor` DOUBLE NULL DEFAULT NULL,
  PRIMARY KEY (`ser_cod`))
ENGINE = InnoDB
AUTO_INCREMENT = 3
DEFAULT CHARACTER SET = latin1;


-- -----------------------------------------------------
-- Table `bancoG2`.`Oferta_Servico`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `bancoG2`.`Oferta_Servico` (
  `Oferta_ofe_cod` INT(11) NOT NULL,
  `Servico_ser_cod` INT(11) NOT NULL,
  `ofe_ser_valor` DECIMAL(5,2) NULL DEFAULT NULL,
  PRIMARY KEY (`Oferta_ofe_cod`, `Servico_ser_cod`),
  INDEX `fk_Oferta_has_Servico_Servico1_idx` (`Servico_ser_cod` ASC) VISIBLE,
  INDEX `fk_Oferta_has_Servico_Oferta1_idx` (`Oferta_ofe_cod` ASC) VISIBLE,
  CONSTRAINT `fk_Oferta_has_Servico_Oferta1`
    FOREIGN KEY (`Oferta_ofe_cod`)
    REFERENCES `bancoG2`.`Oferta` (`ofe_cod`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_Oferta_has_Servico_Servico1`
    FOREIGN KEY (`Servico_ser_cod`)
    REFERENCES `bancoG2`.`Servico` (`ser_cod`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB
DEFAULT CHARACTER SET = latin1;


-- -----------------------------------------------------
-- Table `bancoG2`.`Orcamento`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `bancoG2`.`Orcamento` (
  `orc_cod` INT(11) NOT NULL AUTO_INCREMENT,
  `orc_data` TIMESTAMP(6) NULL DEFAULT NULL,
  `orc_dataValidade` TIMESTAMP(6) NULL DEFAULT NULL,
  `orc_tipo` VARCHAR(45) NULL DEFAULT NULL,
  `orc_desc` VARCHAR(45) NULL DEFAULT NULL,
  `orc_status` VARCHAR(45) NULL DEFAULT NULL,
  `orc_valor` DECIMAL(5,2) NULL DEFAULT NULL,
  `orc_pagamento` VARCHAR(45) NULL DEFAULT NULL,
  `Cliente_cli_cod` INT(11) NOT NULL,
  PRIMARY KEY (`orc_cod`),
  INDEX `fk_Orcamento_Cliente1_idx` (`Cliente_cli_cod` ASC) VISIBLE,
  CONSTRAINT `fk_Orcamento_Cliente1`
    FOREIGN KEY (`Cliente_cli_cod`)
    REFERENCES `bancoG2`.`Cliente` (`cli_cod`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB
DEFAULT CHARACTER SET = latin1;


-- -----------------------------------------------------
-- Table `bancoG2`.`Orcamento_Servico`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `bancoG2`.`Orcamento_Servico` (
  `Orcamento_orc_cod` INT(11) NOT NULL,
  `Servico_ser_cod` INT(11) NOT NULL,
  `orc_ser_valor` DECIMAL(5,2) NULL DEFAULT NULL,
  `orc_ser_qtd` INT(11) NULL DEFAULT NULL,
  PRIMARY KEY (`Orcamento_orc_cod`, `Servico_ser_cod`),
  INDEX `fk_Orcamento_has_Servico_Servico1_idx` (`Servico_ser_cod` ASC) VISIBLE,
  INDEX `fk_Orcamento_has_Servico_Orcamento1_idx` (`Orcamento_orc_cod` ASC) VISIBLE,
  CONSTRAINT `fk_Orcamento_has_Servico_Orcamento1`
    FOREIGN KEY (`Orcamento_orc_cod`)
    REFERENCES `bancoG2`.`Orcamento` (`orc_cod`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_Orcamento_has_Servico_Servico1`
    FOREIGN KEY (`Servico_ser_cod`)
    REFERENCES `bancoG2`.`Servico` (`ser_cod`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB
DEFAULT CHARACTER SET = latin1;


-- -----------------------------------------------------
-- Table `bancoG2`.`Ordem_Servico`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `bancoG2`.`Ordem_Servico` (
  `or_ser_cod` INT(11) NOT NULL AUTO_INCREMENT,
  `or_ser_data` DATE NULL DEFAULT NULL,
  `or_ser_dataFinal` DATE NULL DEFAULT NULL,
  `or_ser_status` VARCHAR(45) NULL DEFAULT NULL,
  `Orcamento_orc_cod` INT(11) NOT NULL,
  `Cliente_cli_cod` INT(11) NOT NULL,
  PRIMARY KEY (`or_ser_cod`),
  INDEX `fk_Ordem_Servico_Orcamento1_idx` (`Orcamento_orc_cod` ASC) VISIBLE,
  INDEX `fk_Ordem_Servico_Cliente1_idx` (`Cliente_cli_cod` ASC) VISIBLE,
  CONSTRAINT `fk_Ordem_Servico_Cliente1`
    FOREIGN KEY (`Cliente_cli_cod`)
    REFERENCES `bancoG2`.`Cliente` (`cli_cod`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_Ordem_Servico_Orcamento1`
    FOREIGN KEY (`Orcamento_orc_cod`)
    REFERENCES `bancoG2`.`Orcamento` (`orc_cod`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB
DEFAULT CHARACTER SET = latin1;


-- -----------------------------------------------------
-- Table `bancoG2`.`Ordem_Servico_Produto`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `bancoG2`.`Ordem_Servico_Produto` (
  `or_ser_cod` INT(11) NOT NULL,
  `pro_cod` INT(11) NOT NULL,
  `or_ser_pro_qtd` INT(11) NULL DEFAULT NULL,
  `or_ser_prod_valor` DECIMAL(5,2) NULL DEFAULT NULL,
  PRIMARY KEY (`or_ser_cod`, `pro_cod`),
  INDEX `fk_Ordem_Servico_has_Produto_Produto1_idx` (`pro_cod` ASC) VISIBLE,
  INDEX `fk_Ordem_Servico_has_Produto_Ordem_Servico1_idx` (`or_ser_cod` ASC) VISIBLE,
  CONSTRAINT `fk_Ordem_Servico_has_Produto_Ordem_Servico1`
    FOREIGN KEY (`or_ser_cod`)
    REFERENCES `bancoG2`.`Ordem_Servico` (`or_ser_cod`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_Ordem_Servico_has_Produto_Produto1`
    FOREIGN KEY (`pro_cod`)
    REFERENCES `bancoG2`.`Produto` (`pro_cod`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB
DEFAULT CHARACTER SET = latin1;


-- -----------------------------------------------------
-- Table `bancoG2`.`Ordem_Servico_Servico`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `bancoG2`.`Ordem_Servico_Servico` (
  `ord_ser_cod` INT(11) NOT NULL,
  `ser_cod` INT(11) NOT NULL,
  `ord_ser_valor` DECIMAL(5,2) NULL DEFAULT NULL,
  `ord_ser_qtd` INT(11) NULL DEFAULT NULL,
  PRIMARY KEY (`ord_ser_cod`, `ser_cod`),
  INDEX `fk_Ordem_Servico_has_Servico_Servico1_idx` (`ser_cod` ASC) VISIBLE,
  INDEX `fk_Ordem_Servico_has_Servico_Ordem_Servico1_idx` (`ord_ser_cod` ASC) VISIBLE,
  CONSTRAINT `fk_Ordem_Servico_has_Servico_Ordem_Servico1`
    FOREIGN KEY (`ord_ser_cod`)
    REFERENCES `bancoG2`.`Ordem_Servico` (`or_ser_cod`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_Ordem_Servico_has_Servico_Servico1`
    FOREIGN KEY (`ser_cod`)
    REFERENCES `bancoG2`.`Servico` (`ser_cod`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB
DEFAULT CHARACTER SET = latin1;


-- -----------------------------------------------------
-- Table `bancoG2`.`Venda_Produto`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `bancoG2`.`Venda_Produto` (
  `Venda_ven_cod` INT(11) NOT NULL,
  `Produto_pro_cod` INT(11) NOT NULL,
  PRIMARY KEY (`Venda_ven_cod`, `Produto_pro_cod`),
  INDEX `fk_Venda_has_Produto_Produto1_idx` (`Produto_pro_cod` ASC) VISIBLE,
  INDEX `fk_Venda_has_Produto_Venda1_idx` (`Venda_ven_cod` ASC) VISIBLE,
  CONSTRAINT `fk_Venda_has_Produto_Produto1`
    FOREIGN KEY (`Produto_pro_cod`)
    REFERENCES `bancoG2`.`Produto` (`pro_cod`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_Venda_has_Produto_Venda1`
    FOREIGN KEY (`Venda_ven_cod`)
    REFERENCES `bancoG2`.`Venda` (`ven_cod`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB
DEFAULT CHARACTER SET = latin1;


SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;
