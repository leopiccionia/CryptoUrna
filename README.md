# CryptoUrna
De vez em quando, ouve-se notícias de defensores da impressão de comprovante individualizado de voto junto à urna eletrônica. Idealmente, o comprovante deveria fornecer mecanismos de verificação de autenticidade do voto, mas sem identificar o eleitor (pois, afinal, voto é secreto).

Lendo um artigo sobre utilização de autômatos celulares em criptografia (infelizmente, não me lembro dos autores), eu tive a inspiração de que há potencial uso delas nesse esquema de autenticação de votos. Implementei, portanto, um gerador de chaves de autenticação em Java baseado no Jogo da Vida (CONWAY, 1970). Sim, é um dos pedaços de código mais estranhos que já escrevi... Dito tudo isso, não realizei ainda qualquer tipo de criptoanálise encima do gerador — que, obviamente, não é aleatório, mas espero que suficientemente pseudoaleatório para fins práticos.

Peço desculpas, desde já, por estar usando uma versão antiga da JDK, o que não me permitiu usar, por exemplo, a classe `java.security.SecureRandom`.
