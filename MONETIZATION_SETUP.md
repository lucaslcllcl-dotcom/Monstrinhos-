# Monetização Monstrinhos 5.1.0

## Google Play Billing
Produtos únicos:
- coins_small -> 300 moedas, consumível
- coins_medium -> 900 moedas, consumível
- remove_ads -> remove intersticiais automáticos, não consumível

Os preços são carregados da Google Play na moeda/localidade do usuário.

## Google Mobile Ads
SDK: com.google.android.gms:play-services-ads:25.5.0
IDs lidos por:
- ADMOB_APP_ID
- ADMOB_REWARDED_ID
- ADMOB_INTERSTITIAL_ID

Sem IDs reais, a build usa IDs oficiais de teste do Google e não gera receita.

## Configuração infantil aplicada
- child-directed treatment = true
- under age of consent = true
- max ad content rating = G
- permissão AD_ID removida
- sem mediação de terceiros
- rewarded opcional = +30 moedas
- interstitial = após 3 fases concluídas, só ao sair do resultado
- remove_ads desativa intersticiais; rewarded continua opt-in

## Play Console
A loja pode mostrar “produto ainda não publicado” enquanto os três produtos não estiverem criados/ativados no Play Console ou quando o app não estiver instalado por uma faixa de teste da Google Play.
