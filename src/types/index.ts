export interface Asset {
  id: string
  symbol: string
  name: string
  type: 'Acción' | 'Bono'
  units: number
  purchasePrice: number
  totalPurchase: number
  currentPrice: number
  totalCurrent: number
  performance: number
  performancePercent: number
}

export interface SoldAsset {
  id: string
  symbol: string
  name: string
  type: 'Acción' | 'Bono'
  units: number
  purchasePrice: number
  salePrice: number
  saleDate: string
  performance: number
  performancePercent: number
}

export interface PortfolioSummary {
  moneyDeposited: number
  liquidMoney: number
  moneyInvested: number
  performance: number
  totalValue: number
  performancePercent: number
}

export interface PortfolioDistribution {
  symbol: string
  value: number
  percentage: number
}

