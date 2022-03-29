--
-- PostgreSQL database dump
--

-- Dumped from database version 14.1
-- Dumped by pg_dump version 14.1

-- Started on 2022-03-29 16:23:06

SET statement_timeout = 0;
SET lock_timeout = 0;
SET idle_in_transaction_session_timeout = 0;
SET client_encoding = 'UTF8';
SET standard_conforming_strings = on;
SELECT pg_catalog.set_config('search_path', '', false);
SET check_function_bodies = false;
SET xmloption = content;
SET client_min_messages = warning;
SET row_security = off;

SET default_tablespace = '';

SET default_table_access_method = heap;

--
-- TOC entry 212 (class 1259 OID 49367)
-- Name: active_accounts; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.active_accounts (
    cust_acc_id character varying NOT NULL
);


ALTER TABLE public.active_accounts OWNER TO postgres;

--
-- TOC entry 209 (class 1259 OID 41166)
-- Name: customers; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.customers (
    id integer NOT NULL,
    firstname character varying(50) NOT NULL,
    lastname character varying(50) NOT NULL,
    pan character varying(10) NOT NULL,
    password character varying NOT NULL,
    balance numeric NOT NULL,
    accstatus character varying NOT NULL,
    acctype character varying
);


ALTER TABLE public.customers OWNER TO postgres;

--
-- TOC entry 210 (class 1259 OID 41195)
-- Name: customers_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

ALTER TABLE public.customers ALTER COLUMN id ADD GENERATED BY DEFAULT AS IDENTITY (
    SEQUENCE NAME public.customers_id_seq
    START WITH 1000
    INCREMENT BY 1
    MINVALUE 1000
    NO MAXVALUE
    CACHE 1
);


--
-- TOC entry 211 (class 1259 OID 41199)
-- Name: employee; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.employee (
    empid integer NOT NULL,
    firstname character varying(50) NOT NULL,
    lastname character varying(50) NOT NULL,
    pwd character varying NOT NULL
);


ALTER TABLE public.employee OWNER TO postgres;

--
-- TOC entry 213 (class 1259 OID 57554)
-- Name: transactions; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.transactions (
    cust_id character varying,
    transaction_type character varying,
    amount numeric,
    sent_to character varying,
    received_from character varying
);


ALTER TABLE public.transactions OWNER TO postgres;

--
-- TOC entry 3324 (class 0 OID 49367)
-- Dependencies: 212
-- Data for Name: active_accounts; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.active_accounts (cust_acc_id) FROM stdin;
1002
1007
1006
1000
1005
1008
\.


--
-- TOC entry 3321 (class 0 OID 41166)
-- Dependencies: 209
-- Data for Name: customers; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.customers (id, firstname, lastname, pan, password, balance, accstatus, acctype) FROM stdin;
1003	nathu	kaliya	7539512582	nathu123	96000	pending	savings
1002	hiraa	laal	7856239632	hira123	529541.0	active	current
1007	bob	marley	5556664442	bob123	41000	active	current
1006	Mohammad	Ali	4561234561	ali123	26000.0	active	savings
1000	Carl	Johnson	7894561233	cj123	85000	active	savings
1004	sattu	patel	4561237896	sattu123	55000	reject	savings
1001	Jack	Horseman	1234567899	jack123	40800.0	active	savings
1005	hath	pag	9639546586	pag123	54500.0	active	savings
1008	Abhishek	Patel	155566	abhi123	1700.0	active	current
\.


--
-- TOC entry 3323 (class 0 OID 41199)
-- Dependencies: 211
-- Data for Name: employee; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.employee (empid, firstname, lastname, pwd) FROM stdin;
1000	admin	admin	admin123
\.


--
-- TOC entry 3325 (class 0 OID 57554)
-- Dependencies: 213
-- Data for Name: transactions; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.transactions (cust_id, transaction_type, amount, sent_to, received_from) FROM stdin;
1001	Sent	4500	1005	\N
1005	Received	4500	\N	1001
1001	Deposit	200	\N	\N
1001	Sent	500	1005	\N
1005	Received	500	\N	1001
1008	Deposit	200	\N	\N
\.


--
-- TOC entry 3331 (class 0 OID 0)
-- Dependencies: 210
-- Name: customers_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.customers_id_seq', 1008, true);


--
-- TOC entry 3181 (class 2606 OID 49373)
-- Name: active_accounts active_accounts_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.active_accounts
    ADD CONSTRAINT active_accounts_pkey PRIMARY KEY (cust_acc_id);


--
-- TOC entry 3177 (class 2606 OID 41170)
-- Name: customers customers_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.customers
    ADD CONSTRAINT customers_pkey PRIMARY KEY (id);


--
-- TOC entry 3179 (class 2606 OID 41205)
-- Name: employee employee_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.employee
    ADD CONSTRAINT employee_pkey PRIMARY KEY (empid);


-- Completed on 2022-03-29 16:23:06

--
-- PostgreSQL database dump complete
--
